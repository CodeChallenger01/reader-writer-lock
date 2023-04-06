import java.util.concurrent.locks.ReentrantLock

class ReadWriteLock {
  private val reentrantLock = new ReentrantLock()
  private val readCond = reentrantLock.newCondition()
  private val writeCond = reentrantLock.newCondition()
  private var writerCount = 0
  private var readerCount = 0
  private var writerWaiting = false

  /* To acquire the read lock */
  def acquireReadLock(): Unit = {
    reentrantLock.lock()
    try {
      while (writerCount > 0 || writerWaiting) {
        readCond.await()
      }
      readerCount += 1
    } finally {
      reentrantLock.unlock()
    }
  }

  /* To release the read lock */
  def releaseReadLock(): Unit = {
    reentrantLock.lock()
    try {
      readerCount -= 1
      if (readerCount == 0) {
        writeCond.signalAll()
      }
    } finally {
      reentrantLock.unlock()
    }
  }

  /* To acquire the write lock */
  def acquireWriteLock(): Unit = {
    reentrantLock.lock()
    try {
      writerWaiting = true
      while (writerCount > 0 || readerCount > 0) {
        writeCond.await()
      }
      writerWaiting = false
      writerCount += 1
    } finally {
      reentrantLock.unlock()
    }
  }

  /* To release the write lock */
  def releaseWriteLock(): Unit = {
    reentrantLock.lock()
    try {
      writerCount -= 1
      if (writerCount == 0) {
        readCond.signalAll()
      } else {
        writeCond.signal()
      }
    } finally {
      reentrantLock.unlock()
    }
  }
}

/* This class contains multiple threads */
class MultipleThreads {
  private val readWriteLock = new ReadWriteLock

  val threadReadOne = new Runnable {
    override def run(): Unit = {
      readWriteLock.acquireReadLock()
      println("Acquiring Read Lock 1")
      Thread.sleep(1000)
      println("Releasing Read Lock 1")
      readWriteLock.releaseReadLock()
    }
  }

  val threadReadTwo = new Runnable {
    override def run(): Unit = {
      readWriteLock.acquireReadLock()
      println("Acquiring Read Lock 2")
      Thread.sleep(1000)
      println("Releasing Read Lock 2")
      readWriteLock.releaseReadLock()
    }
  }
  val threadWriteOne = new Runnable {
    override def run(): Unit = {
      readWriteLock.acquireWriteLock()
      println("Acquiring Write Lock 1")
      Thread.sleep(1000)
      println("Releasing Write Lock 1")
      readWriteLock.releaseWriteLock()
    }
  }
  val threadWriteTwo = new Runnable {
    override def run(): Unit = {
      readWriteLock.acquireWriteLock()
      println("Acquiring Write Lock 2")
      Thread.sleep(1000)
      println("Releasing Write Lock 2")
      readWriteLock.releaseWriteLock()
    }
  }
}