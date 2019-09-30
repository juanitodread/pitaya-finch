package org.juanitodread.pitayafinch.formatters

trait NumberFormatter {
  def round(confidence: Double, precision: Int = 3): Double = {
    require(precision > 0 && precision < 6)
    val decimals: Double = Math.pow(10, precision)
    Math.rint(confidence * decimals) / decimals
  }
}
