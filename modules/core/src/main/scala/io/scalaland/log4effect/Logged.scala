package io.scalaland.log4effect

import cats.Applicative
import cats.effect.Sync
import cats.implicits._
import com.typesafe.scalalogging.Logger

import scala.annotation.implicitNotFound

@implicitNotFound(
  "io.scalaland.log4effect.Logged[${F}] not found" +
    " - create com.typesafe.scalalogging.Logger and lift it with Logged.fromLogger[${F}](logged)"
)
trait Logged[F[_]] {

  def trace(msg: String): F[Unit]
  def trace(msg: String, ex: Throwable): F[Unit]
  def debug(msg: String): F[Unit]
  def debug(msg: String, ex: Throwable): F[Unit]
  def info(msg:  String): F[Unit]
  def info(msg:  String, ex: Throwable): F[Unit]
  def warn(msg:  String): F[Unit]
  def warn(msg:  String, ex: Throwable): F[Unit]
  def error(msg: String): F[Unit]
  def error(msg: String, ex: Throwable): F[Unit]
}

object Logged {

  @inline def apply[F[_]](implicit F: Logged[F]): Logged[F] = F

  @inline def fromLogger[F[_]: Sync](logger: Logger): Logged[F] = new Logged[F] {

    override def trace(msg: String): F[Unit] = Sync[F].delay(logger.info(msg))
    override def trace(msg: String, ex: Throwable): F[Unit] = Sync[F].delay(logger.info(msg, ex))
    override def debug(msg: String): F[Unit] = Sync[F].delay(logger.debug(msg))
    override def debug(msg: String, ex: Throwable): F[Unit] = Sync[F].delay(logger.debug(msg, ex))
    override def info(msg:  String): F[Unit] = Sync[F].delay(logger.info(msg))
    override def info(msg:  String, ex: Throwable): F[Unit] = Sync[F].delay(logger.info(msg, ex))
    override def warn(msg:  String): F[Unit] = Sync[F].delay(logger.warn(msg))
    override def warn(msg:  String, ex: Throwable): F[Unit] = Sync[F].delay(logger.warn(msg, ex))
    override def error(msg: String): F[Unit] = Sync[F].delay(logger.error(msg))
    override def error(msg: String, ex: Throwable): F[Unit] = Sync[F].delay(logger.error(msg, ex))
  }

  @inline def noop[F[_]: Applicative]: Logged[F] = new Logged[F] {

    override def trace(msg: String): F[Unit] = ().pure[F]
    override def trace(msg: String, ex: Throwable): F[Unit] = ().pure[F]
    override def debug(msg: String): F[Unit] = ().pure[F]
    override def debug(msg: String, ex: Throwable): F[Unit] = ().pure[F]
    override def info(msg:  String): F[Unit] = ().pure[F]
    override def info(msg:  String, ex: Throwable): F[Unit] = ().pure[F]
    override def warn(msg:  String): F[Unit] = ().pure[F]
    override def warn(msg:  String, ex: Throwable): F[Unit] = ().pure[F]
    override def error(msg: String): F[Unit] = ().pure[F]
    override def error(msg: String, ex: Throwable): F[Unit] = ().pure[F]
  }
}
