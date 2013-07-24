package example

object Bench extends BenchSuite(ichi.bench.Thyme.warmed(verbose = print)) with RangeTest {
  def run {
    def report(name: String, result: Any): Unit =
      println(name, result)

    report("up: old vs. while", th.benchOffPairWarm()(up)(whileLoopUp))
    report("up: paulp vs. old", th.benchOffPairWarm()(upPaulp)(up))
    report("up: paulp vs. while", th.benchOffPairWarm()(upPaulp)(whileLoopUp))
    report("down: old vs. while", th.benchOffPairWarm()(down)(whileLoopDown))
  }
}

object Main extends App {
  Bench.run
}
