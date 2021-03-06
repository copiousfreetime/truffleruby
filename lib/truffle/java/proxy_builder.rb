# Copyright (c) 2017 Oracle and/or its affiliates. All rights reserved. This
# code is released under a tri EPL/GPL/LGPL license. You can use it,
# redistribute it and/or modify it under the terms of the:
#
# Eclipse Public License version 1.0, or
# GNU General Public License version 2, or
# GNU Lesser General Public License version 2.1.

module JavaUtilities
  class JavaProxyBuilder

    def initialize(a_proxy, java_class)
      @java_class = java_class
      @proxy = a_proxy
      @static_members = {}
      @instance_members = {}
      reserve_names()
    end

    def reserve_names()
      both = ['__id__', '__send__', 'instance_of?']
      instance = ['class', 'initialize']
      static = ['new']
      (both + instance).map { |name| add_to_instance(name, Reserved.new) }
      (both + static).map { |name| add_to_singleton(name, Reserved.new) }
    end

    def add_to_singleton(name, thing, maybe_getter=false, maybe_setter=false)
      add_to_map(@static_members, name, thing, maybe_getter, maybe_setter)
    end

    def add_to_instance(name, thing, maybe_getter=false, maybe_setter=false)
      add_to_map(@instance_members, name, thing, maybe_getter, maybe_setter)
    end

    def add_to_map(a_map, name, thing, maybe_getter, maybe_setter)
      add_to_map_internal(a_map, name, thing)
      alias_names(name, maybe_getter, maybe_setter) do |a|
        add_to_map_internal(a_map, a, Alias.new(a, name))
      end
    end

    def alias_names(name, maybe_getter, maybe_setter)
      return if name.upcase == name
      parts = name.split(/(?=[[:upper:]])/).map { |v| v.downcase }
      return if parts.size == 1
      getter = maybe_getter & (parts[0] == 'get')
      setter = maybe_setter & (parts[0] == 'set')
      yield parts.join('_')
      yield parts[1..-1].join('_') + '?' if parts[0] == 'is'
      yield parts[1..-1].join('_') if getter
      yield parts[1..-1].join('_') + '=' if setter
    end

    def add_to_map_internal(a_map, name, thing)
      return a_map[name] = thing if ! a_map.has_key?(name)
      return a_map[name] = thing if a_map[name].precedence > thing.precedence
      return a_map[name].combine_with(thing) if a_map[name].precedence == thing.precedence
      a_map[name]
    end

    # The following methods for adding fields, methods, constructors
    # and interfaces cannot use idiomatic Ruby to traverse the Java
    # arrays as Java interop classes may not have been bootstrapped
    # sufficiently to allow it.

    def add_interfaces
      interfaces = Java.invoke_java_method(CLASS_GET_INTERFACES, @java_class)
      interfaces_size = JavaUtilities.java_array_size(interfaces)
      (0...interfaces_size).each do |i|
        interface_proxy = JavaUtilities.make_proxy(JavaUtilities.java_array_get(interfaces, i))
        @proxy.include(interface_proxy) unless @proxy.ancestors.include?(interface_proxy)
      end
      self
    end

    def add_static_fields
      fields = Java.invoke_java_method(CLASS_GET_DECLARED_FIELDS, @java_class)
      fields_size = JavaUtilities.java_array_size(fields)
      (0...fields_size).each { |i| add_static_field(JavaUtilities.java_array_get(fields, i)) }
      self
    end

    def add_static_field(f)
      mh = JavaUtilities.unreflect_getter(f)
      unless mh.nil?
        is_static = JavaUtilities.static_field?(f)
        return if !is_static
        name = Interop.from_java_string(Java.invoke_java_method(FIELD_GET_NAME, f))
        is_const = JavaUtilities.constant_field?(f)
        const_val = ::JavaUtilities.wrap_java_value(Java.invoke_java_method(mh)) if is_const
        getter = -> { ::JavaUtilities.wrap_java_value(Java.invoke_java_method(mh)) }
        if !JavaUtilities.final_field?(f)
          mh = JavaUtilities.unreflect_getter(f)
          setter = -> v {
            ::JavaUtilities.wrap_java_value(Java.invoke_java_method(mh, v)) }
        end
        add_to_singleton(name, Field.new(name, getter, setter, is_const, const_val))
      end
    end

    def add_instance_fields
      fields = Java.invoke_java_method(CLASS_GET_DECLARED_FIELDS, @java_class)
      fields_size = JavaUtilities.java_array_size(fields)
      (0...fields_size).each { |i| add_instance_field(JavaUtilities.java_array_get(fields, i)) }
      self
    end

    def add_instance_field(f)
      mh = JavaUtilities.unreflect_getter(f)
      unless mh.nil?
        is_static = JavaUtilities.static_field?(f)
        return if is_static
        name = Interop.from_java_string(Java.invoke_java_method(FIELD_GET_NAME, f))
        getter = -> {
          ::JavaUtilities.wrap_java_value(Java.invoke_java_method(mh, java_object)) }
        if !JavaUtilities.final_field?(f)
          mh = JavaUtilities.unreflect_getter(f)
          setter = -> v {
            ::JavaUtilities.wrap_java_value(Java.invoke_java_method(mh, java_object, v)) }
        end
        add_to_instance(name, Field.new(name, getter, setter))
      end
    end

    def add_static_methods
      methods = Java.invoke_java_method(CLASS_GET_DECLARED_METHODS, @java_class)
      methods_size = JavaUtilities.java_array_size(methods)
      (0...methods_size).each do |i|
        m = JavaUtilities.java_array_get(methods, i)
        if JavaUtilities.static_method?(m)
          mh = JavaUtilities.unreflect_method(m)
          unless mh.nil?
            name = Interop.from_java_string(Java.invoke_java_method(METHOD_GET_NAME, m))
            a_method = Method.new(name, mh)
            arity = a_method.arity
            add_to_singleton(name, a_method, arity == 0, arity == 1)
          end
        end
      end
      self
    end

    def add_instance_methods
      methods = Java.invoke_java_method(CLASS_GET_DECLARED_METHODS, @java_class)
      methods_size = JavaUtilities.java_array_size(methods)
      (0...methods_size).each do |i|
        m = JavaUtilities.java_array_get(methods, i)
        if !JavaUtilities.static_method?(m)
          mh = JavaUtilities.unreflect_method(m)
          unless mh.nil?
            name = Interop.from_java_string(Java.invoke_java_method(METHOD_GET_NAME, m))
            a_method = Method.new(name, mh)
            arity = a_method.arity
            add_to_instance(name, a_method, arity == 1, arity == 2)
          end
        end
      end
      self
    end

    def add_array_accessors
      return self unless Java.invoke_java_method(JavaUtilities::CLASS_IS_ARRAY, @java_class)
      array_getter = Java.invoke_java_method(JavaUtilities::ARRAY_GETTER_GETTER, @java_class)
      array_setter = Java.invoke_java_method(JavaUtilities::ARRAY_SETTER_GETTER, @java_class)
      array_length = JavaUtilities::REFLECT_ARRAY_LENGTH
      ms = {'[]' => array_getter, '[]=' => array_setter, 'size' => array_length}
      ms.each do |name, mh|
        a_method = Method.new(name, mh)
        arity = a_method.arity
        add_to_instance(name, a_method, arity == 1, arity == 2)
      end
      self
    end

    def add_constructors
      constructors = Java.invoke_java_method(CLASS_GET_CONSTRUCTORS, @java_class)
      constructors_size = JavaUtilities.java_array_size(constructors)
      (0...constructors_size).each do |i|
        c = JavaUtilities.java_array_get(constructors, i)
        mh = JavaUtilities.unreflect_constructor(c)
        unless mh.nil?
          add_to_singleton('new', Constructor.new(mh))
        end
      end
      self
    end

    def build
      self.add_interfaces().
        add_static_fields().
        add_static_methods().
        add_instance_fields().
        add_instance_methods().
        add_array_accessors().
        add_constructors()

      @static_members.values.each do |m|
        m.add_to_proxy(@proxy, true)
      end
      @instance_members.values.each do |m|
        m.add_to_proxy(@proxy, false)
      end
    end
    @proxy
  end

  class Field
    def initialize(name, getter, setter, is_const=false, const_val=nil)
      @name, @getter, @setter, @is_const, @const_val =
          name, getter, setter, is_const, const_val
    end

    def precedence
      2
    end

    def add_to_proxy(a_proxy, static)
      if @is_const
        begin
          a_proxy.const_set(@name, @const_val)
        rescue NameError
          nil
        end
      end
      message = if static
                  :define_singleton_method
                else
                  :define_method
                end
      a_proxy.__send__(message, @name, @getter)
      a_proxy.__send__(message, @name, @setter) unless @xssetter.nil?
    end

    def combine_with(a_field)
      raise Exception, 'We should never see overloaded fields.'
    end
  end

  class Method
    attr_reader :dispatcher

    def initialize(name, a_method)
      @name = name
      @dispatcher = JavaDispatcher.new(a_method)
    end

    def precedence
      1
    end

    def add_to_proxy(a_proxy, static)
      message = if static
                  :define_singleton_method
                else
                  :define_method
                end
      @dispatcher.method_for_dispatch -> method {
        wrapped = if static
                    -> *args { method[*args] }
                  else
                    -> *args { method[self, *args] }
                  end
        a_proxy.__send__(message, @name, wrapped)
      }
    end

    def combine_with(a_method)
      @dispatcher.combine_with(a_method.dispatcher)
    end

    def arity
      @dispatcher.basic_arity
    end
  end

  class Constructor < Method
    def initialize(a_method)
      super('new', a_method)
    end

    def precedence
      -1
    end
  end

  class Alias

    attr_reader :name
    attr_reader :target

    def initialize(name, target)
      @name = name
      @target = target
    end

    def precedence
      3
    end

    def combine_with(an_alias)
      if self.target != an_alias.target
        raise NameError, "Clash on alias #{name} between #{target} and #{an_alias.target}"
      end
    end

    def add_to_proxy(a_proxy, static)
      if static
        a_proxy.__send__(:define_singleton_method, @name, a_proxy.method(@target))
      else
        a_proxy.__send__(:define_method, @name, a_proxy.instance_method(@target))
      end
    end
  end

  class Reserved
    def precedence
      0
    end

    def combine_with(reserved)
      self
    end

    def add_to_proxy(a_proxy, static)
    end
  end
end
