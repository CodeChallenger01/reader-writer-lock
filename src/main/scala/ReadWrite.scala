import java.util.concurrent.locks.ReentrantReadWriteLock

class ReadWriteLock {
  private val reentrantReadWriteLock = new ReentrantReadWriteLock()
  private var counter = 0

  /* A method to perform the writer part to increment the counter value */
  def writeCount(): String = {
    reentrantReadWriteLock.writeLock().lock()
    try {
      println("\nAcquiring Writing Lock on :" + Thread.currentThread().getName)
      counter = counter + 1
      s"Start Writing Count :$counter"
    }
    finally {
      reentrantReadWriteLock.writeLock().unlock()
    }
  }

  /* A method to perform the reader part to read the counter value */
  def readCount(): String = {
    reentrantReadWriteLock.readLock().lock()
    try {
      println("\nAcquiring Reading Lock on :" + Thread.currentThread().getName)
      s"Start Read Count :$counter"
    }
    finally {
      reentrantReadWriteLock.readLock().unlock()
    }
  }
}

/* This class contains multiple threads */
class MultipleThreads {
  private val readWriteLock = new ReadWriteLock

  /* 1st Thread for reading counter value */
  val threadReadOne = new Runnable {
    override def run(): Unit = {
      println(readWriteLock.readCount() + "\nReleasing Reading Lock :" + Thread.currentThread().getName)
      Thread.sleep(1000)
    }
  }

  /* 2nd Thread for reading counter value */
  val threadReadTwo = new Runnable {
    override def run(): Unit = {
      println(readWriteLock.readCount() + "\nReleasing Reading Lock :" + Thread.currentThread().getName)
      Thread.sleep(1000)
    }
  }

  /* 3rd Thread for writing(increment) counter value */
  val threadWriteOne = new Runnable {
    override def run(): Unit = {
      println(readWriteLock.writeCount() + "\nReleasing Writing Lock :" + Thread.currentThread().getName)
      Thread.sleep(1000)
    }
  }

  /* 4th Thread for writing(increment) counter value */
  val threadWriteTwo = new Runnable {
    override def run(): Unit = {
      println(readWriteLock.writeCount() + "\nReleasing Writing Lock :" + Thread.currentThread().getName)
      Thread.sleep(1000)
    }
  }
}