import java.util.concurrent.locks.ReentrantReadWriteLock

class ReadWriteLock {
  private val reentrantReadWriteLock = new ReentrantReadWriteLock()
  private var counter = 0

  /* A method to perform the writer part to increment the counter value */
  def writeCount(): String = {
    reentrantReadWriteLock.writeLock().lock()
    try {
      counter = counter + 1
      s"Start Writing Count :$counter\nFinished Writing :$counter"
    }
    finally {
      reentrantReadWriteLock.writeLock().unlock()
    }
  }

  /* A method to perform the reader part to read the counter value */
  def readCount(): String = {
    reentrantReadWriteLock.readLock().lock()
    try {
      s"Start Read Count :$counter\nFinished Reading :$counter"
    }
    finally {
      reentrantReadWriteLock.readLock().unlock()
    }
  }
}

/* This class contains multiple threads */
class MultipleThreads {
  private val readWriteLock = new ReadWriteLock

  val threadReadOne = new Runnable {
    override def run(): Unit = {
      println(readWriteLock.readCount())
      Thread.sleep(1000)
    }
  }
  val threadReadTwo = new Runnable {
    override def run(): Unit = {
      println(readWriteLock.readCount())
      Thread.sleep(1000)
    }
  }

  val threadWriteOne = new Runnable {
    override def run(): Unit = {
      println(readWriteLock.writeCount())
      Thread.sleep(1000)
    }
  }

  val threadWriteTwo = new Runnable {
    override def run(): Unit = {
      println(readWriteLock.writeCount())
      Thread.sleep(1000)
    }
  }
}