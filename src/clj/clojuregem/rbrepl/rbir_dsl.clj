(ns clojuregem.rbrepl.rbir-dsl
    (:require [clojure.tools.analyzer.jvm :as ana.jvm]
              [clojure.tools.analyzer.passes.jvm.emit-form :as e]))


(e/emit-hygienic-form (ana.jvm/analyze '(let [a 1 a a] a)))


  def test_empty_stmt
    assert_parses(
      nil,
      %q{})
  end

  def test_nil
    assert_parses(
      s(:nil),
      %q{nil},
      %q{~~~ expression})
  end

  def test_nil_expression
    assert_parses(
      s(:begin),
      %q{()},
      %q{^ begin
        | ^ end
        |~~ expression})

    assert_parses(
      s(:kwbegin),
      %q{begin end},
      %q{~~~~~ begin
        |      ~~~ end
        |~~~~~~~~~ expression})
  end

  def test_true
    assert_parses(
      s(:true),
      %q{true},
      %q{~~~~ expression})
  end

  def test_false
    assert_parses(
      s(:false),
      %q{false},
      %q{~~~~~ expression})
  end

  def test_int
    assert_parses(
      s(:int, 42),
      %q{42},
      %q{~~ expression})

    assert_parses(
      s(:int, -42),
      %q{-42},
      %q{^ operator
        |~~~ expression})
  end

  def test_int___LINE__
    assert_parses(
      s(:int, 1),
      %q{__LINE__},
      %q{~~~~~~~~ expression})
  end

  def test_float
    assert_parses(
      s(:float, 1.33),
      %q{1.33},
      %q{~~~~ expression})

    assert_parses(
      s(:float, -1.33),
      %q{-1.33},
      %q{^ operator
        |~~~~~ expression})
  end

  def test_rational
    assert_parses(
      s(:rational, Rational(42)),
      %q{42r},
      %q{~~~ expression},
      SINCE_2_1)

    assert_parses(
      s(:rational, Rational(421, 10)),
      %q{42.1r},
      %q{~~~~~ expression},
      SINCE_2_1)
  end

  def test_complex
    assert_parses(
      s(:complex, Complex(0, 42)),
      %q{42i},
      %q{~~~ expression},
      SINCE_2_1)

    assert_parses(
      s(:complex, Complex(0, Rational(42))),
      %q{42ri},
      %q{~~~~ expression},
      SINCE_2_1)

    assert_parses(
      s(:complex, Complex(0, 42.1)),
      %q{42.1i},
      %q{~~~~~ expression},
      SINCE_2_1)

    assert_parses(
      s(:complex, Complex(0, Rational(421, 10))),
      %q{42.1ri},
      %q{~~~~~~ expression},
      SINCE_2_1)
  end

  # Strings

  def test_string_plain
    assert_parses(
      s(:str, 'foobar'),
      %q{'foobar'},
      %q{^ begin
        |       ^ end
        |~~~~~~~~ expression})

    assert_parses(
      s(:str, 'foobar'),
      %q{%q(foobar)},
      %q{^^^ begin
        |         ^ end
        |~~~~~~~~~~ expression})
  end

  def test_string_interp
    assert_parses(
      s(:dstr,
        s(:str, 'foo'),
        s(:begin, s(:lvar, :bar)),
        s(:str, 'baz')),
      %q{"foo#{bar}baz"},
      %q{^ begin
        |             ^ end
        |    ^^ begin (begin)
        |         ^ end (begin)
        |    ~~~~~~ expression (begin)
        |~~~~~~~~~~~~~~ expression})
  end

  def test_string_dvar
    assert_parses(
      s(:dstr,
        s(:ivar, :@a),
        s(:str, ' '),
        s(:cvar, :@@a),
        s(:str, ' '),
        s(:gvar, :$a)),
      %q{"#@a #@@a #$a"})
  end

  def test_string_concat
    assert_parses(
      s(:dstr,
        s(:dstr,
          s(:str, 'foo'),
          s(:ivar, :@a)),
        s(:str, 'bar')),
      %q{"foo#@a" "bar"},
      %q{^ begin (dstr)
        |       ^ end (dstr)
        |         ^ begin (str)
        |             ^ end (str)
        |~~~~~~~~~~~~~~ expression})
  end

  def test_string___FILE__
    assert_parses(
      s(:str, '(assert_parses)'),
      %q{__FILE__},
      %q{~~~~~~~~ expression})
  end

  def test_character
    assert_parses(
      s(:str, 'a'),
      %q{?a},
      %q{^ begin
        |~~ expression},
      SINCE_1_9)

    assert_parses(
      s(:int, 97),
      %q{?a},
      %q{~~ expression},
      %w(1.8))
  end

