package io.scalaland.log4effect

import cats.effect.SyncIO
import cats.implicits._
import com.typesafe.scalalogging.Logger
import org.specs2.mutable.Specification

final class LoggingTests extends Specification {

  import syntax._

  implicit val logged: Logged[SyncIO] = Logged.fromLogger[SyncIO](Logger("tests"))

  "syntax" should {

    val i = 1
    val d = 1.0
    val s = "test"
    val t = Test(i, d, s)

    "must compile for TRACE" in {
      trace"$i $d $s $t"[SyncIO].unsafeRunSync()
      trace"$i $d $s $t".withEx[SyncIO](new Exception("with ex")).unsafeRunSync()
      1 mustEqual 1
    }

    "must compile for DEBUG" in {
      debug"$i $d $s $t"[SyncIO].unsafeRunSync()
      debug"$i $d $s $t".withEx[SyncIO](new Exception("with ex")).unsafeRunSync()
      1 mustEqual 1
    }

    "must compile for INFO" in {
      info"$i $d $s $t"[SyncIO].unsafeRunSync()
      info"$i $d $s $t".withEx[SyncIO](new Exception("with ex")).unsafeRunSync()
      1 mustEqual 1
    }

    "must compile for WARN" in {
      warn"$i $d $s $t"[SyncIO].unsafeRunSync()
      warn"$i $d $s $t".withEx[SyncIO](new Exception("with ex")).unsafeRunSync()
      1 mustEqual 1
    }

    "must compile for TRACE" in {
      error"$i $d $s $t"[SyncIO].unsafeRunSync()
      error"$i $d $s $t".withEx[SyncIO](new Exception("with ex")).unsafeRunSync()
      1 mustEqual 1
    }
  }
}
