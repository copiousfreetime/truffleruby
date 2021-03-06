require 'rbconfig'

class MSpecScript

  def self.child_process?
    ENV.key? "MSPEC_RUNNER"
  end

  def self.windows?
    ENV.key?('WINDIR') || ENV.key?('windir')
  end

  def self.linux?
    RbConfig::CONFIG['host_os'].include?('linux')
  end

  def self.darwin?
    RbConfig::CONFIG['host_os'].include?('darwin')
  end

  def self.solaris?
    RbConfig::CONFIG['host_os'].include?('solaris')
  end

  if child_process?
    system_ruby = `which ruby`.chomp
    if !system_ruby.empty? and `#{system_ruby} --version`.start_with?('ruby ')
      SYSTEM_RUBY = system_ruby
    end
  end

  set :command_line, [
    "spec/ruby/command_line"
  ]

  set :security, [
    "spec/ruby/security",
    "^spec/ruby/security/cve_2017_17742_spec.rb" # :library_cext
  ]

  set :language, [
    "spec/ruby/language"
  ]

  set :core, [
    "spec/ruby/core",
  ]

  # Specs that need Sulong and should be tested in the Sulong gate
  library_cext_specs = %w[
    spec/ruby/library/etc
    spec/ruby/library/openssl
    spec/ruby/library/rbconfig/sizeof
    spec/ruby/library/syslog
    spec/ruby/library/yaml
    spec/ruby/library/zlib
    spec/ruby/security/cve_2017_17742_spec.rb
  ]

  set :library, [
    "spec/ruby/library",

    # Issues with monkey patching breaking our core
    "^spec/ruby/library/mathn",

    # Trying to enable breaks a lot of things
    "^spec/ruby/library/net",

    # Unsupported
    "^spec/ruby/library/win32ole",

    # Tested separately as they need Sulong
    *library_cext_specs.map { |path| "^#{path}" }
  ]

  set :library_cext, library_cext_specs

  set :capi, [
    "spec/ruby/optional/capi"
  ]

  set :truffle, [
    "spec/truffle",
    "^spec/truffle/capi"
  ]

  set :truffle_capi, [
    "spec/truffle/capi"
  ]

  set :ruby25, [
    "spec/ruby/core/kernel/yield_self_spec.rb",
    "spec/ruby/core/method/case_compare_spec.rb",
    "spec/ruby/core/enumerable/all_spec.rb",
    "spec/ruby/core/enumerable/any_spec.rb",
    "spec/ruby/core/enumerable/none_spec.rb",
    "spec/ruby/core/enumerable/one_spec.rb",
    "spec/ruby/core/exception/full_message_spec.rb",
    "spec/ruby/core/hash/transform_keys_spec.rb",
    "spec/ruby/library/mathn/mathn_spec.rb",
  ]

  set :backtrace_filter, /mspec\//

  set :tags_patterns, [
    [%r(^.*/command_line/),             'spec/tags/command_line/'],
    [%r(^.*/security/),                 'spec/tags/security/'],
    [%r(^.*/language/),                 'spec/tags/language/'],
    [%r(^.*/core/),                     'spec/tags/core/'],
    [%r(^.*/library/),                  'spec/tags/library/'],
    [%r(^.*/optional/capi/),            'spec/tags/optional/capi/'],
    [%r(^.*/truffle),                   'spec/tags/truffle/'],
    [/_spec.rb$/,                       '_tags.txt']
  ]

  if defined?(TruffleRuby) && TruffleRuby.native?
    # exclude specs tagged with 'aot' and 'graalvm'
    set :xtags, (get(:xtags) || []) + ['aot', 'graalvm']
  end

  if windows?
    # exclude specs tagged with 'windows'
    set :xtags, (get(:xtags) || []) + ['windows']
  elsif linux?
    # exclude specs tagged with 'linux'
    set :xtags, (get(:xtags) || []) + ['linux']
  elsif darwin?
    # exclude specs tagged with 'darwin'
    set :xtags, (get(:xtags) || []) + ['darwin']
  elsif solaris?
    # exclude specs tagged with 'solaris'
    set :xtags, (get(:xtags) || []) + ['solaris']
  end

  # Enable features
  MSpec.enable_feature :fiber
  MSpec.enable_feature :fiber_library
  MSpec.disable_feature :fork
  MSpec.enable_feature :encoding
  MSpec.enable_feature :readline

  set :files, get(:command_line) + get(:language) + get(:core) + get(:library) + get(:truffle) + get(:security)

  # All specs, including specs needing C-extensions support.
  # 2.4/2.5 specs are not included as they need to run in a separate process.
  set :all, get(:files) + get(:capi) + get(:truffle_capi) + get(:library_cext)
end

if MSpecScript.child_process?
  if version = ENV["PRETEND_RUBY_VERSION"]
    ::VersionGuard::FULL_RUBY_VERSION = SpecVersion.new(version)
  elsif ARGV.include? ":ruby25"
    ::VersionGuard::FULL_RUBY_VERSION = SpecVersion.new("2.5.3")
  end

  # We do not use Ruby 2.5's FrozenError yet
  def frozen_error_class
    RuntimeError
  end
end

if i = ARGV.index('slow') and ARGV[i-1] == '--excl-tag' and MSpecScript.child_process?
  require 'mspec'

  class SlowSpecsTagger
    def initialize
      MSpec.register :exception, self
    end

    def exception(state)
      if state.exception.is_a? SlowSpecException
        tag = SpecTag.new
        tag.tag = 'slow'
        tag.description = "#{state.describe} #{state.it}"
        MSpec.write_tag(tag)
      end
    end
  end

  class SlowSpecException < Exception
  end

  require 'timeout'

  slow_methods = [
    [Object, [:ruby_exe, :ruby_cmd]],
    [ObjectSpace.singleton_class, [:each_object]],
    [GC.singleton_class, [:start]],
    [Kernel, [:system, :`]],
    [Kernel.singleton_class, [:system, :`]],
    [Timeout.singleton_class, [:timeout]],
  ]

  module Kernel
    alias_method :"mspec_old_`", :`
    private :"mspec_old_`"
  end

  slow_methods.each do |klass, meths|
    klass.class_exec do
      meths.each do |meth|
        define_method(meth) do |*args, &block|
          if MSpec.current && MSpec.current.state # an example is running
            raise SlowSpecException, "Was tagged as slow as it uses #{meth}(). Rerun specs."
          else
            send("mspec_old_#{meth}", *args, &block)
          end
        end
        # Keep visibility for Kernel instance methods
        private meth if klass == Kernel
      end
    end
  end

  SlowSpecsTagger.new
end
