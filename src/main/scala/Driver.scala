import java.util.concurrent.Executors

object Driver extends App {
  private val multipleThreads = new MultipleThreads
  /* A thread pool that takes two thread at a time to perform the operation */
  private val threadPoolExecutor = Executors.newFixedThreadPool(2)
  threadPoolExecutor.execute(multipleThreads.threadWriteOne)
  threadPoolExecutor.execute(multipleThreads.threadReadTwo)
  threadPoolExecutor.execute(multipleThreads.threadReadOne)
  threadPoolExecutor.execute(multipleThreads.threadWriteTwo)
  threadPoolExecutor.shutdown()
}
