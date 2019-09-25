package io.scalaland.log4effect
package syntax

import scala.annotation.implicitNotFound

@implicitNotFound("Couldn't find cats.Show to lift - provide one or, call .toString explicitly")
trait ShowLifted { def value(): String }
