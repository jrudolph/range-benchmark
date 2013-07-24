package example

object Bench extends BenchSuite(ichi.bench.Thyme.warmed(verbose = print)) with RangeTest {
  def run {
    def report(name: String, result: Any): Unit =
      println(name, result)

    report("up: paulp vs. old", th.benchOffPairWarm()(paulpUp)(up))
    report("up: paulp vs. while", th.benchOffPairWarm()(paulpUp)(whileLoopUp))
    report("up: old vs. while", th.benchOffPairWarm()(up)(whileLoopUp))
    report("up: sum vs. while", th.benchOffPairWarm()(sum)(whileLoopUp))
    report("up: paulp sum vs. while", th.benchOffPairWarm()(sumPaulp)(whileLoopUp))

    report("up nested: old vs. while", th.benchOffPairWarm()(upNested)(whileLoopUpNested))
    report("up nested: paulp vs. while", th.benchOffPairWarm()(paulpUpNested)(whileLoopUpNested))
    report("up nested: sum vs. while", th.benchOffPairWarm()(sumNested)(whileLoopUpNested))
  }
}

object Main extends App {
  Bench.run
}
