# log4effect

[![https://travis-ci.org/scalalandio/log4effect](https://api.travis-ci.org/scalalandio/log4effect.svg?branch=master)](https://travis-ci.org/scalalandio/log4effect)
[![Maven Central](https://img.shields.io/maven-central/v/io.scalaland/log4effect_2.12.svg)](http://search.maven.org/#search%7Cga%7C1%7Clog4effect)
[![License](http://img.shields.io/:license-Apache%202-green.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)

If you missed some syntax for logging with Cats Effect, then here it is.

## Getting started

Library is available for Scala 2.11, 2.12, 2.13.

Add it with:

```scala
libraryDependencies += "io.scalaland" %% "log4effect" % log4EffectVersion
```

## Usage

Logging is done using `Logged` type class:

```scala
import cats.effect.Sync
import io.scalaland.log4effect.Logged

def operation[F[_]: Sync: Logged] = for {
  a <- Sync[F].defer(1 + 1)
  b <- Sync[F].defer(2 + 2)
  _ <- Logged[F].info(s"a = $a b=$b")
} yield a -> b
```

If you want you can use interpolator syntax - it assumes that there is
`cats.Show` instance for any value that you use in it.

```scala
import cats.implicits._
import io.scalaland.log4effect.Logged
import io.scalaland.log4effect.syntax._

val i = 1
val d = 1.0
val s = "test"

def logs[F[_]: Monad: Logged] = for {
  _ <- trace"$i $d $s"[F]
  _ <- trace"$i $d $s".withEx[F](new Exception("with ex"))
  _ <- debug"$i $d $s"[F]
  _ <- debug"$i $d $s".withEx[F](new Exception("with ex"))
  _ <- info"$i $d $s"[F]
  _ <- info"$i $d $s".withEx[F](new Exception("with ex"))
  _ <- warn"$i $d $s"[F]
  _ <- warn"$i $d $s".withEx[F](new Exception("with ex"))
  _ <- error"$i $d $s"[F]
  _ <- error"$i $d $s".withEx[F](new Exception("with ex"))
} yield ()
```

If there is no `cats.Show` you'd have to call `.toString` explicitly.
