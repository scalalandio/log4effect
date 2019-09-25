package io.scalaland.log4effect

import cats.Show

final case class Test(i: Int, d: Double, s: String)
object Test {
  implicit val show: Show[Test] = (t: Test) => s"Test(i = ${t.i}, d = ${t.d}, s = ${t.s})"
}
