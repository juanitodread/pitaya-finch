package org.juanitodread.pitayafinch.formatters

import org.juanitodread.pitayafinch.UnitSpec

class NumberFormatterSpec extends UnitSpec {
  val formatter: NumberFormatter = new NumberFormatter {}

  "A NumberFormatter" should "format the confidence result number with default precision" in {
    assert(formatter.round(0.123456789) === 0.123)
  }

  it should "format the confidence result number with min precision number allowed" in {
    assert(formatter.round(0.123456789, 1) === 0.1)
  }

  it should "format the confidence result number with max precision number allowed" in {
    assert(formatter.round(0.123456789, 5) === 0.12346) // 5 rounds to 6
  }

  it should "fail when zero precision value is provided" in {
    assertThrows[IllegalArgumentException] {
      formatter.round(0.123456789, 0)
    }
  }

  it should "fail when negative precision value is provided" in {
    assertThrows[IllegalArgumentException] {
      formatter.round(0.123456789, -1)
    }
  }

  it should "fail when precision value provided is greater than max allowed" in {
    assertThrows[IllegalArgumentException] {
      formatter.round(0.123456789, 5 + 1)
    }
  }
}
