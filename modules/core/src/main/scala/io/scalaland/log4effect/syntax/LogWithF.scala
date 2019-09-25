package io.scalaland.log4effect
package syntax

trait LogWithF {
  def apply[F[_]:  Logged]: F[Unit]
  def withEx[F[_]: Logged](ex: Throwable): F[Unit]
}
