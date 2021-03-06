# frozen_string_literal: false
require 'test/unit'

class TestTrace < Test::Unit::TestCase
  def test_trace
    $x = 1234
    $y = 0
    trace_var :$x, proc{$y = $x}
    $x = 40414
    assert_equal($x, $y)

    untrace_var :$x
    $x = 19660208
    assert_not_equal($x, $y)

    trace_var :$x, proc{$x *= 2}
    $x = 5
    assert_equal(10, $x)

    untrace_var :$x
  end

  def test_trace_tainted_proc
    $x = 1234
    s = proc { $y = :foo }
    trace_var :$x, s
    s.taint
    $x = 42
    assert_equal(:foo, $y)
  ensure
    untrace_var :$x
  end

  def test_trace_proc_that_raises_exception
    $x = 1234
    trace_var :$x, proc { raise }
    assert_raise(RuntimeError) { $x = 42 }
  ensure
    untrace_var :$x
  end

  def test_trace_string
    $x = 1234
    trace_var :$x, "$y = :bar"
    $x = 42
    assert_equal(:bar, $y)
  ensure
    untrace_var :$x
  end

  def test_trace_break
    bug2722 = '[ruby-core:31783]'
    a = Object.new.extend(Enumerable)
    def a.each
      yield
    end
    assert(Thread.start {
             Thread.current.add_trace_func(proc{})
             a.any? {true}
           }.value, bug2722)
  end

  def test_trace_stackoverflow
    assert_normal_exit("#{<<-"begin;"}\n#{<<~"end;"}", timeout: 60)
    begin;
      require 'tracer'
      class HogeError < StandardError
        def to_s
          message.upcase        # disable tailcall optimization
        end
      end
      Tracer.stdout = open(IO::NULL, "w")
      Tracer.on
      HogeError.new.to_s
    end;
  end
end
