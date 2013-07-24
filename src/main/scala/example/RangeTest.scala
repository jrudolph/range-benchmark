package example

trait RangeTest { self: BenchSuite ⇒
  val up = th.Warm {
    var counter = 0
    for (i ← 1 to 1000) counter += i * i
    counter
  }
  val upPaulp = th.Warm {
    var counter = 0
    for (i ← scala.collection.immutable.IntRange(1, 1000, 1, true)) counter += i * i
    counter
  }
  val down = th.Warm {
    var counter = 0
    for (i ← 1000 to 1 by -1) counter += i * i
    counter
  }
  val sum = th.Warm {
    (1 to 1000).map(i ⇒ i * i).sum
  }
  val foldLeft = th.Warm {
    (1 to 1000).map(i ⇒ i * i).foldLeft(0)(_ + _)
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
