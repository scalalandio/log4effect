package io.scalaland.log4effect
package syntax

import cats.Show
import cats.implicits._

trait LoggedSyntax {

  implicit def showLiftedFromShow[A: Show](value: A): ShowLifted = () => value.show

  implicit def loggedContextFromStringContext(sc: StringContext): LoggedContext = new LoggedContext(sc)
}
