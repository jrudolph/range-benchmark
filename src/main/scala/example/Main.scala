package example

object Bench extends BenchSuite(ichi.bench.Thyme.warmed(verbose = print)) with RangeTest {
  def run {
    def report(test: String, c1: String, c2: String, result: (Any, Any)): Unit =
      println(s"Test: $test\nFirst: '$c1' Second: '$c2'\n\n${result._2}\n\n")

    report("up", "paulp", "old", th.benchOffPairWarm()(paulpUp)(up))
    report("up", "paulp", "while", th.benchOffPairWarm()(paulpUp)(whileLoopUp))
    report("up", "old", "while", th.benchOffPairWarm()(up)(whileLoopUp))
    report("up", "sum", "while", th.benchOffPairWarm()(sum)(whileLoopUp))
    report("up", "paulp sum", "while", th.benchOffPairWarm()(sumPaulp)(whileLoopUp))

    report("up nested", "old", "while", th.benchOffPairWarm()(upNested)(whileLoopUpNested))
    report("up nested", "paulp", "while", th.benchOffPairWarm()(paulpUpNested)(whileLoopUpNested))
    report("up nested", "sum", "while", th.benchOffPairWarm()(sumNested)(whileLoopUpNested))
  }
}

object Main extends App {
  Bench.run
}
