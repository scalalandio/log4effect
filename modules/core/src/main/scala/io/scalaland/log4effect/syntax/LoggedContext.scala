package io.scalaland.log4effect
package syntax

final class LoggedContext(val sc: StringContext) extends AnyVal {

  def trace(args: ShowLifted*): LogWithF = new LogWithF {
    def apply[F[_]:  Logged]: F[Unit] = Logged[F].trace(sc.raw(args.map(_.value()): _*))
    def withEx[F[_]: Logged](ex: Throwable): F[Unit] = Logged[F].trace(sc.raw(args.map(_.value()): _*), ex)
  }
  def debug(args: ShowLifted*): LogWithF = new LogWithF {
    def apply[F[_]:  Logged]: F[Unit] = Logged[F].debug(sc.raw(args.map(_.value()): _*))
    def withEx[F[_]: Logged](ex: Throwable): F[Unit] = Logged[F].error(sc.raw(args.map(_.value()): _*), ex)
  }
  def info(args: ShowLifted*): LogWithF = new LogWithF {
    def apply[F[_]:  Logged]: F[Unit] = Logged[F].info(sc.raw(args.map(_.value()): _*))
    def withEx[F[_]: Logged](ex: Throwable): F[Unit] = Logged[F].error(sc.raw(args.map(_.value()): _*), ex)
  }
  def warn(args: ShowLifted*): LogWithF = new LogWithF {
    def apply[F[_]:  Logged]: F[Unit] = Logged[F].warn(sc.raw(args.map(_.value()): _*))
    def withEx[F[_]: Logged](ex: Throwable): F[Unit] = Logged[F].error(sc.raw(args.map(_.value()): _*), ex)
  }
  def error(args: ShowLifted*): LogWithF = new LogWithF {
    def apply[F[_]:  Logged]: F[Unit] = Logged[F].error(sc.raw(args.map(_.value()): _*))
    def withEx[F[_]: Logged](ex: Throwable): F[Unit] = Logged[F].error(sc.raw(args.map(_.value()): _*), ex)
  }
}
