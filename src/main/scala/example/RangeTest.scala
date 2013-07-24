package example

trait RangeTest { self: BenchSuite ⇒
  val up = th.Warm {
    var counter = 0
    for (i ← 1 to 1000) counter += i * i
    counter
  }
  val paulpUp = th.Warm {
    var counter = 0
    for (i ← scala.collection.immutable.IntRange(1, 1000, 1, true)) counter += i * i
    counter
  }
  val whileLoopUp = th.Warm {
    var counter = 0
    var i = 1
    while (i <= 1000) {
      counter += i * i
      i += 1
    }
    counter
  }
  val sum = th.Warm {
    (1 to 1000).map(i ⇒ i * i).sum
  }
  val sumPaulp = th.Warm {
    var counter = 0
    scala.collection.immutable.IntRange(1, 1000, 1, true).map(i ⇒ i * i).sum
    counter
  }

  val upNested = th.Warm {
    var counter = 0
    for {
      i ← 1 to 100
      j ← 1 to 100
    } counter += i * j
    counter
  }

  val paulpUpNested = th.Warm {
    var counter = 0
    for {
      i ← scala.collection.immutable.IntRange(1, 100, 1, true)
      j ← scala.collection.immutable.IntRange(1, 100, 1, true)
    } counter += i * j
    counter
  }
  val whileLoopUpNested = th.Warm {
    var counter = 0
    var i = 1
    while (i <= 100) {
      var j = 1
      while (j <= 100) {
        counter += i * j
        j += 1
      }

      i += 1
    }
    counter
  }

  val sumNested = th.Warm {
    (for {
      i ← (1 to 100)
      j ← (1 to 100)
    } yield i * j).sum
  }
  val foldLeft = th.Warm {
    (1 to 1000).map(i ⇒ i * i).foldLeft(0)(_ + _)
  }

  val down = th.Warm {
    var counter = 0
    for (i ← 1000 to 1 by -1) counter += i * i
    counter
  }

  val whileLoopDown = th.Warm {
    var counter = 0
    var i = 1000
    while (i >= 1) {
      counter += i * i
      i -= 1
    }
    counter
  }
}
