/*
 * Copyright (c) 2016 Oracle and/or its affiliates. All rights reserved. This
 * code is released under a tri EPL/GPL/LGPL license. You can use it,
 * redistribute it and/or modify it under the terms of the:
 *
 * Eclipse Public License version 1.0
 * GNU General Public License version 2
 * GNU Lesser General Public License version 2.1
 */
package org.truffleruby.options;

// This file is automatically generated by options.yml with 'jt build options'

import javax.annotation.Generated;

@Generated("tool/generate-options.rb")
public class OptionsCatalog {

    public static final StringOptionDescription HOME = new StringOptionDescription("home", "The location of the TruffleRuby installation files", null);
    public static final StringOptionDescription LAUNCHER = new StringOptionDescription("launcher", "The location of the TruffleRuby launcher program", null);
    public static final StringArrayOptionDescription LOAD_PATHS = new StringArrayOptionDescription("load_paths", "Load paths", new String[]{});
    public static final StringArrayOptionDescription REQUIRED_LIBRARIES = new StringArrayOptionDescription("required_libraries", "Required libraries", new String[]{});
    public static final ByteStringOptionDescription INLINE_SCRIPT = new ByteStringOptionDescription("inline_script", "Inline script", null);
    public static final StringArrayOptionDescription ARGUMENTS = new StringArrayOptionDescription("arguments", "Command line arguments for the Ruby program", new String[]{});
    public static final StringOptionDescription DISPLAYED_FILE_NAME = new StringOptionDescription("displayed_file_name", "Displayed file name", null);
    public static final BooleanOptionDescription READ_RUBYOPT = new BooleanOptionDescription("read_rubyopt", "Read RUBYOPT and TRUFFLERUBYOPT environment variables", true);
    public static final BooleanOptionDescription DEBUG = new BooleanOptionDescription("debug", "Debug", false);
    public static final VerbosityOptionDescription VERBOSITY = new VerbosityOptionDescription("verbosity", "Verbosity", Verbosity.FALSE);
    public static final BooleanOptionDescription FROZEN_STRING_LITERALS = new BooleanOptionDescription("frozen_string_literals", "Use frozen string literals", false);
    public static final BooleanOptionDescription RUBYGEMS = new BooleanOptionDescription("rubygems", "Use RubyGems", true);
    public static final BooleanOptionDescription PATCHING = new BooleanOptionDescription("patching", "Use patching", true);
    public static final BooleanOptionDescription DID_YOU_MEAN = new BooleanOptionDescription("did_you_mean", "Use did_you_mean", true);
    public static final StringOptionDescription INTERNAL_ENCODING = new StringOptionDescription("internal_encoding", "Internal encoding", null);
    public static final StringOptionDescription EXTERNAL_ENCODING = new StringOptionDescription("external_encoding", "External encoding", null);
    public static final BooleanOptionDescription POLYGLOT_STDIO = new BooleanOptionDescription("ployglot.stdio", "Use standard IO streams from the PolyglotEngine", true);
    public static final BooleanOptionDescription SYNC_STDIO = new BooleanOptionDescription("sync.stdio", "Sync operations on standard IO streams", true);
    public static final BooleanOptionDescription PLATFORM_USE_JAVA = new BooleanOptionDescription("platform.use_java", "Use a pure-Java platform", false);
    public static final BooleanOptionDescription TRACE_CALLS = new BooleanOptionDescription("trace.calls", "Support tracing (set_trace_func", true);
    public static final BooleanOptionDescription COVERAGE_GLOBAL = new BooleanOptionDescription("coverage.global", "Run coverage for all code and print results on exit", false);
    public static final BooleanOptionDescription INLINE_JS = new BooleanOptionDescription("inline_js", "Allow inline JavaScript", false);
    public static final StringOptionDescription CORE_LOAD_PATH = new StringOptionDescription("core.load_path", "Location to load the Truffle core library from", "resource:/truffleruby");
    public static final BooleanOptionDescription STDLIB_AS_INTERNAL = new BooleanOptionDescription("stdlib_as_internal", "Mark stdlib sources (really", true);
    public static final BooleanOptionDescription LAZY_TRANSLATION_CORE = new BooleanOptionDescription("lazy_translation.core", "Lazily translation of core source files", true);
    public static final BooleanOptionDescription LAZY_TRANSLATION_USER = new BooleanOptionDescription("lazy_translation.user", "Lazily translation of stdlib", false);
    public static final BooleanOptionDescription LAZY_TRANSLATION_LOG = new BooleanOptionDescription("lazy_translation.log", "Log lazy translations from the parser AST to the Truffle AST", false);
    public static final IntegerOptionDescription ARRAY_UNINITIALIZED_SIZE = new IntegerOptionDescription("array.uninitialized_size", "How large an Array to allocate when we have no other information to go on", 32);
    public static final IntegerOptionDescription ARRAY_SMALL = new IntegerOptionDescription("array.small", "Maximum size of an Array to consider small for optimisations", 3);
    public static final IntegerOptionDescription HASH_PACKED_ARRAY_MAX = new IntegerOptionDescription("hash.packed_array.max", "Maximum size of a Hash to consider using the packed array storage strategy for", 3);
    public static final BooleanOptionDescription ROPE_LAZY_SUBSTRINGS = new BooleanOptionDescription("rope.lazy_substrings", "Indicates whether a substring operation on a rope should be performed lazily", true);
    public static final BooleanOptionDescription ROPE_PRINT_INTERN_STATS = new BooleanOptionDescription("rope.print_intern_stats", "Print interned rope stats at application exit", false);
    public static final IntegerOptionDescription ROPE_DEPTH_THRESHOLD = new IntegerOptionDescription("rope.depth_threshold", "Threshold value at which ropes will be rebalanced (indirectly controls flattening as well)", 128);
    public static final IntegerOptionDescription GLOBAL_VARIABLE_MAX_INVALIDATIONS = new IntegerOptionDescription("global_variable.max_invalidations", "Maximum number of times a global variable can be changed to be considered constant", 10);
    public static final IntegerOptionDescription DEFAULT_CACHE = new IntegerOptionDescription("default_cache", "Default size for caches", 8);
    public static final IntegerOptionDescription METHOD_LOOKUP_CACHE = new IntegerOptionDescription("method_lookup.cache", "Method lookup cache size", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription DISPATCH_CACHE = new IntegerOptionDescription("dispatch.cache", "Dispatch (various forms of method call) cache size", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription YIELD_CACHE = new IntegerOptionDescription("yield.cache", "Yield cache size", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription METHOD_TO_PROC_CACHE = new IntegerOptionDescription("to_proc.cache", "Method#to_proc cache size", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription IS_A_CACHE = new IntegerOptionDescription("is_a.cache", "Kernel#is_a? and #kind_of? cache size", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription BIND_CACHE = new IntegerOptionDescription("bind.cache", "Cache size of test for being able to bind a method to a module", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription CONSTANT_CACHE = new IntegerOptionDescription("constant.cache", "Constant cache size", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription INSTANCE_VARIABLE_CACHE = new IntegerOptionDescription("instance_variable.cache", "Instance variable cache size", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription BINDING_LOCAL_VARIABLE_CACHE = new IntegerOptionDescription("binding_local_variable.cache", "Binding#local_variable_get/set cache size", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription SYMBOL_TO_PROC_CACHE = new IntegerOptionDescription("symbol_to_proc.cache", "Symbol#to_proc cache size", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription ALLOCATE_CLASS_CACHE = new IntegerOptionDescription("allocate_class.cache", "Allocation size class cache size", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription PACK_CACHE = new IntegerOptionDescription("pack.cache", "Array#pack cache size", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription UNPACK_CACHE = new IntegerOptionDescription("unpack.cache", "String#unpack cache size", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription EVAL_CACHE = new IntegerOptionDescription("eval.cache", "eval cache size", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription CLASS_CACHE = new IntegerOptionDescription("class.cache", ".class and .metaclass cache size", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription ENCODING_COMPATIBLE_QUERY_CACHE = new IntegerOptionDescription("encoding_compatible_query.cache", "Encoding.compatible? cache size", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription ENCODING_LOADED_CLASSES_CACHE = new IntegerOptionDescription("encoding_loaded_classes.cache", "Cache size of encoding operations based on anticipated number of total active encodings", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription THREAD_CACHE = new IntegerOptionDescription("thread.cache", "Cache size of operations that depend on a particular thread", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription ROPE_CLASS_CACHE = new IntegerOptionDescription("rope_class.cache", "Cache size for rope operations that depend on a concrete rope implementation to avoid virtual calls", 6);
    public static final IntegerOptionDescription INTEROP_CONVERT_CACHE = new IntegerOptionDescription("interop.convert.cache", "Cache size for converting values for interop", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription INTEROP_EXECUTE_CACHE = new IntegerOptionDescription("interop.execute.cache", "Cache size for interop EXECUTE messages", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription INTEROP_READ_CACHE = new IntegerOptionDescription("interop.read.cache", "Cache size for interop READ messages", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription INTEROP_WRITE_CACHE = new IntegerOptionDescription("interop.write.cache", "Cache size for interop WRITE messages", DEFAULT_CACHE.getDefaultValue());
    public static final IntegerOptionDescription INTEROP_INVOKE_CACHE = new IntegerOptionDescription("interop.invoke.cache", "Cache size for interop INVOKE messages", DEFAULT_CACHE.getDefaultValue());
    public static final BooleanOptionDescription CLONE_DEFAULT = new BooleanOptionDescription("clone.default", "Default option for cloning", true);
    public static final BooleanOptionDescription INLINE_DEFAULT = new BooleanOptionDescription("inline.default", "Default option for inlining", true);
    public static final BooleanOptionDescription CORE_ALWAYS_CLONE = new BooleanOptionDescription("core.always_clone", "Always clone built-in core methods", CLONE_DEFAULT.getDefaultValue());
    public static final BooleanOptionDescription INLINE_NEEDS_CALLER_FRAME = new BooleanOptionDescription("inline_needs_caller_frame", "Inline methods that need their caller frame", INLINE_DEFAULT.getDefaultValue());
    public static final BooleanOptionDescription YIELD_ALWAYS_CLONE = new BooleanOptionDescription("yield.always_clone", "Always clone yields", CLONE_DEFAULT.getDefaultValue());
    public static final BooleanOptionDescription YIELD_ALWAYS_INLINE = new BooleanOptionDescription("yield.always_inline", "Always inline yields", INLINE_DEFAULT.getDefaultValue());
    public static final BooleanOptionDescription METHODMISSING_ALWAYS_CLONE = new BooleanOptionDescription("method_missing.always_clone", "Always clone #method_missing", CLONE_DEFAULT.getDefaultValue());
    public static final BooleanOptionDescription METHODMISSING_ALWAYS_INLINE = new BooleanOptionDescription("method_missing.always_inline", "Always inline #method_missing", INLINE_DEFAULT.getDefaultValue());
    public static final IntegerOptionDescription PACK_UNROLL_LIMIT = new IntegerOptionDescription("pack.unroll", "If a pack or unpack expression has a loop less than this many iterations", 4);
    public static final IntegerOptionDescription PACK_RECOVER_LOOP_MIN = new IntegerOptionDescription("pack.recover", "If a pack or unpack expression is longer than this", 32);
    public static final BooleanOptionDescription EXCEPTIONS_STORE_JAVA = new BooleanOptionDescription("exceptions.store_java", "Store the Java exception with the Ruby backtrace", false);
    public static final BooleanOptionDescription EXCEPTIONS_PRINT_JAVA = new BooleanOptionDescription("exceptions.print_java", "Print Java exceptions at the point of translating them to Ruby exceptions", false);
    public static final BooleanOptionDescription EXCEPTIONS_PRINT_UNCAUGHT_JAVA = new BooleanOptionDescription("exceptions.print_uncaught_java", "Print uncaught Java exceptions at the point of translating them to Ruby exceptions", false);
    public static final BooleanOptionDescription EXCEPTIONS_TRANSLATE_ASSERT = new BooleanOptionDescription("exceptions.translate_assert", "Translate failed Java assertions to Ruby exceptions", true);
    public static final BooleanOptionDescription BACKTRACES_HIDE_CORE_FILES = new BooleanOptionDescription("backtraces.hide_core_files", "Hide core source files in backtraces", true);
    public static final BooleanOptionDescription BACKTRACES_INTERLEAVE_JAVA = new BooleanOptionDescription("backtraces.interleave_java", "Interleave Java stacktraces into the Ruby backtrace", false);
    public static final IntegerOptionDescription BACKTRACES_LIMIT = new IntegerOptionDescription("backtraces.limit", "Limit the size of Ruby backtraces", 9999);
    public static final BooleanOptionDescription BACKTRACES_OMIT_UNUSED = new BooleanOptionDescription("backtraces.omit_unused", "Omit backtraces that should be unused as they have pure rescue expressions", true);
    public static final BooleanOptionDescription BASICOPS_INLINE = new BooleanOptionDescription("basic_ops.inline", "Inline basic operations (like Fixnum operators) in the AST without a call", true);
    public static final BooleanOptionDescription GRAAL_WARNING_UNLESS = new BooleanOptionDescription("graal.warn_unless", "Warn unless the JVM has the Graal compiler", true);
    public static final BooleanOptionDescription SHARED_OBJECTS_ENABLED = new BooleanOptionDescription("shared.objects", "Enable shared objects", true);
    public static final BooleanOptionDescription SHARED_OBJECTS_DEBUG = new BooleanOptionDescription("shared.objects.debug", "Print information about shared objects", false);
    public static final BooleanOptionDescription SHARED_OBJECTS_FORCE = new BooleanOptionDescription("shared.objects.force", "Force sharing of objects roots at startup", false);
    public static final BooleanOptionDescription SHARED_OBJECTS_SHARE_ALL = new BooleanOptionDescription("shared.objects.share_all", "Consider all objects as shared", false);
    public static final BooleanOptionDescription CEXTS_LOG_LOAD = new BooleanOptionDescription("cexts.log.load", "Log loading of cexts", false);
    public static final BooleanOptionDescription LOG_DYNAMIC_CONSTANT_LOOKUP = new BooleanOptionDescription("constant.dynamic_lookup.log", "Log source code positions where dynamic constant lookup is performed", false);
    public static final BooleanOptionDescription OPTIONS_LOG = new BooleanOptionDescription("options.log", "Log the final value of all options", false);
    public static final BooleanOptionDescription LOG_LOAD = new BooleanOptionDescription("log.load", "Log loading files", false);
    public static final BooleanOptionDescription LOG_FEATURE_LOCATION = new BooleanOptionDescription("log.feature_location", "Log the process of finding features", false);
    public static final IntegerOptionDescription FRAME_VARIABLE_ACCESS_LIMIT = new IntegerOptionDescription("frame.variable.access.limit", "Maximum number of specialisations for nodes which access variables in another frame", 5);
    
    public static OptionDescription<?> fromName(String name) {
        switch (name) {
            case "home":
                return HOME;
            case "launcher":
                return LAUNCHER;
            case "load_paths":
                return LOAD_PATHS;
            case "required_libraries":
                return REQUIRED_LIBRARIES;
            case "inline_script":
                return INLINE_SCRIPT;
            case "arguments":
                return ARGUMENTS;
            case "displayed_file_name":
                return DISPLAYED_FILE_NAME;
            case "read_rubyopt":
                return READ_RUBYOPT;
            case "debug":
                return DEBUG;
            case "verbosity":
                return VERBOSITY;
            case "frozen_string_literals":
                return FROZEN_STRING_LITERALS;
            case "rubygems":
                return RUBYGEMS;
            case "patching":
                return PATCHING;
            case "did_you_mean":
                return DID_YOU_MEAN;
            case "internal_encoding":
                return INTERNAL_ENCODING;
            case "external_encoding":
                return EXTERNAL_ENCODING;
            case "ployglot.stdio":
                return POLYGLOT_STDIO;
            case "sync.stdio":
                return SYNC_STDIO;
            case "platform.use_java":
                return PLATFORM_USE_JAVA;
            case "trace.calls":
                return TRACE_CALLS;
            case "coverage.global":
                return COVERAGE_GLOBAL;
            case "inline_js":
                return INLINE_JS;
            case "core.load_path":
                return CORE_LOAD_PATH;
            case "stdlib_as_internal":
                return STDLIB_AS_INTERNAL;
            case "lazy_translation.core":
                return LAZY_TRANSLATION_CORE;
            case "lazy_translation.user":
                return LAZY_TRANSLATION_USER;
            case "lazy_translation.log":
                return LAZY_TRANSLATION_LOG;
            case "array.uninitialized_size":
                return ARRAY_UNINITIALIZED_SIZE;
            case "array.small":
                return ARRAY_SMALL;
            case "hash.packed_array.max":
                return HASH_PACKED_ARRAY_MAX;
            case "rope.lazy_substrings":
                return ROPE_LAZY_SUBSTRINGS;
            case "rope.print_intern_stats":
                return ROPE_PRINT_INTERN_STATS;
            case "rope.depth_threshold":
                return ROPE_DEPTH_THRESHOLD;
            case "global_variable.max_invalidations":
                return GLOBAL_VARIABLE_MAX_INVALIDATIONS;
            case "default_cache":
                return DEFAULT_CACHE;
            case "method_lookup.cache":
                return METHOD_LOOKUP_CACHE;
            case "dispatch.cache":
                return DISPATCH_CACHE;
            case "yield.cache":
                return YIELD_CACHE;
            case "to_proc.cache":
                return METHOD_TO_PROC_CACHE;
            case "is_a.cache":
                return IS_A_CACHE;
            case "bind.cache":
                return BIND_CACHE;
            case "constant.cache":
                return CONSTANT_CACHE;
            case "instance_variable.cache":
                return INSTANCE_VARIABLE_CACHE;
            case "binding_local_variable.cache":
                return BINDING_LOCAL_VARIABLE_CACHE;
            case "symbol_to_proc.cache":
                return SYMBOL_TO_PROC_CACHE;
            case "allocate_class.cache":
                return ALLOCATE_CLASS_CACHE;
            case "pack.cache":
                return PACK_CACHE;
            case "unpack.cache":
                return UNPACK_CACHE;
            case "eval.cache":
                return EVAL_CACHE;
            case "class.cache":
                return CLASS_CACHE;
            case "encoding_compatible_query.cache":
                return ENCODING_COMPATIBLE_QUERY_CACHE;
            case "encoding_loaded_classes.cache":
                return ENCODING_LOADED_CLASSES_CACHE;
            case "thread.cache":
                return THREAD_CACHE;
            case "rope_class.cache":
                return ROPE_CLASS_CACHE;
            case "interop.convert.cache":
                return INTEROP_CONVERT_CACHE;
            case "interop.execute.cache":
                return INTEROP_EXECUTE_CACHE;
            case "interop.read.cache":
                return INTEROP_READ_CACHE;
            case "interop.write.cache":
                return INTEROP_WRITE_CACHE;
            case "interop.invoke.cache":
                return INTEROP_INVOKE_CACHE;
            case "clone.default":
                return CLONE_DEFAULT;
            case "inline.default":
                return INLINE_DEFAULT;
            case "core.always_clone":
                return CORE_ALWAYS_CLONE;
            case "inline_needs_caller_frame":
                return INLINE_NEEDS_CALLER_FRAME;
            case "yield.always_clone":
                return YIELD_ALWAYS_CLONE;
            case "yield.always_inline":
                return YIELD_ALWAYS_INLINE;
            case "method_missing.always_clone":
                return METHODMISSING_ALWAYS_CLONE;
            case "method_missing.always_inline":
                return METHODMISSING_ALWAYS_INLINE;
            case "pack.unroll":
                return PACK_UNROLL_LIMIT;
            case "pack.recover":
                return PACK_RECOVER_LOOP_MIN;
            case "exceptions.store_java":
                return EXCEPTIONS_STORE_JAVA;
            case "exceptions.print_java":
                return EXCEPTIONS_PRINT_JAVA;
            case "exceptions.print_uncaught_java":
                return EXCEPTIONS_PRINT_UNCAUGHT_JAVA;
            case "exceptions.translate_assert":
                return EXCEPTIONS_TRANSLATE_ASSERT;
            case "backtraces.hide_core_files":
                return BACKTRACES_HIDE_CORE_FILES;
            case "backtraces.interleave_java":
                return BACKTRACES_INTERLEAVE_JAVA;
            case "backtraces.limit":
                return BACKTRACES_LIMIT;
            case "backtraces.omit_unused":
                return BACKTRACES_OMIT_UNUSED;
            case "basic_ops.inline":
                return BASICOPS_INLINE;
            case "graal.warn_unless":
                return GRAAL_WARNING_UNLESS;
            case "shared.objects":
                return SHARED_OBJECTS_ENABLED;
            case "shared.objects.debug":
                return SHARED_OBJECTS_DEBUG;
            case "shared.objects.force":
                return SHARED_OBJECTS_FORCE;
            case "shared.objects.share_all":
                return SHARED_OBJECTS_SHARE_ALL;
            case "cexts.log.load":
                return CEXTS_LOG_LOAD;
            case "constant.dynamic_lookup.log":
                return LOG_DYNAMIC_CONSTANT_LOOKUP;
            case "options.log":
                return OPTIONS_LOG;
            case "log.load":
                return LOG_LOAD;
            case "log.feature_location":
                return LOG_FEATURE_LOCATION;
            case "frame.variable.access.limit":
                return FRAME_VARIABLE_ACCESS_LIMIT;
            default:
                return null;
        }
    }

    public static OptionDescription<?>[] allDescriptions() {
        return new OptionDescription<?>[] {
            HOME,
            LAUNCHER,
            LOAD_PATHS,
            REQUIRED_LIBRARIES,
            INLINE_SCRIPT,
            ARGUMENTS,
            DISPLAYED_FILE_NAME,
            READ_RUBYOPT,
            DEBUG,
            VERBOSITY,
            FROZEN_STRING_LITERALS,
            RUBYGEMS,
            PATCHING,
            DID_YOU_MEAN,
            INTERNAL_ENCODING,
            EXTERNAL_ENCODING,
            POLYGLOT_STDIO,
            SYNC_STDIO,
            PLATFORM_USE_JAVA,
            TRACE_CALLS,
            COVERAGE_GLOBAL,
            INLINE_JS,
            CORE_LOAD_PATH,
            STDLIB_AS_INTERNAL,
            LAZY_TRANSLATION_CORE,
            LAZY_TRANSLATION_USER,
            LAZY_TRANSLATION_LOG,
            ARRAY_UNINITIALIZED_SIZE,
            ARRAY_SMALL,
            HASH_PACKED_ARRAY_MAX,
            ROPE_LAZY_SUBSTRINGS,
            ROPE_PRINT_INTERN_STATS,
            ROPE_DEPTH_THRESHOLD,
            GLOBAL_VARIABLE_MAX_INVALIDATIONS,
            DEFAULT_CACHE,
            METHOD_LOOKUP_CACHE,
            DISPATCH_CACHE,
            YIELD_CACHE,
            METHOD_TO_PROC_CACHE,
            IS_A_CACHE,
            BIND_CACHE,
            CONSTANT_CACHE,
            INSTANCE_VARIABLE_CACHE,
            BINDING_LOCAL_VARIABLE_CACHE,
            SYMBOL_TO_PROC_CACHE,
            ALLOCATE_CLASS_CACHE,
            PACK_CACHE,
            UNPACK_CACHE,
            EVAL_CACHE,
            CLASS_CACHE,
            ENCODING_COMPATIBLE_QUERY_CACHE,
            ENCODING_LOADED_CLASSES_CACHE,
            THREAD_CACHE,
            ROPE_CLASS_CACHE,
            INTEROP_CONVERT_CACHE,
            INTEROP_EXECUTE_CACHE,
            INTEROP_READ_CACHE,
            INTEROP_WRITE_CACHE,
            INTEROP_INVOKE_CACHE,
            CLONE_DEFAULT,
            INLINE_DEFAULT,
            CORE_ALWAYS_CLONE,
            INLINE_NEEDS_CALLER_FRAME,
            YIELD_ALWAYS_CLONE,
            YIELD_ALWAYS_INLINE,
            METHODMISSING_ALWAYS_CLONE,
            METHODMISSING_ALWAYS_INLINE,
            PACK_UNROLL_LIMIT,
            PACK_RECOVER_LOOP_MIN,
            EXCEPTIONS_STORE_JAVA,
            EXCEPTIONS_PRINT_JAVA,
            EXCEPTIONS_PRINT_UNCAUGHT_JAVA,
            EXCEPTIONS_TRANSLATE_ASSERT,
            BACKTRACES_HIDE_CORE_FILES,
            BACKTRACES_INTERLEAVE_JAVA,
            BACKTRACES_LIMIT,
            BACKTRACES_OMIT_UNUSED,
            BASICOPS_INLINE,
            GRAAL_WARNING_UNLESS,
            SHARED_OBJECTS_ENABLED,
            SHARED_OBJECTS_DEBUG,
            SHARED_OBJECTS_FORCE,
            SHARED_OBJECTS_SHARE_ALL,
            CEXTS_LOG_LOAD,
            LOG_DYNAMIC_CONSTANT_LOOKUP,
            OPTIONS_LOG,
            LOG_LOAD,
            LOG_FEATURE_LOCATION,
            FRAME_VARIABLE_ACCESS_LIMIT,
        };
    }

}
