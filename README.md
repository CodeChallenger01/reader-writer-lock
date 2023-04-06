# This is reader-writer-problem solution

**Step 1:** Create a scala file inside src/main/scala/ by the Name ReadWrite.Scala    
**Step 2:** Inside it create two classes.     
1. **First class** name should be **ReadWriteLock**, and it contains two method.    
1.1 **writeCount()** => Inside this method we have to first apply the lock and after that increment the value of count and unlock the shared resource.    
1.2 **readCount()** => Inside this method we have to just read or print the current counter value inside the lock and after that again unlock it.

2. **Second Class of Name MultipleThreads** : In this class we have to make instantiate of **ReadWriteLock** Class with **multiple threads** that extends the Runnable interface. Inside the runnable interface we have to call the methods of ReadWriteLock Class. For this I created four threads two four read and two for write.    
>  **val threadReadOne = new Runnable** {     
> override def run(): Unit = {    
> println(readWriteLock.readCount())    
> Thread.sleep(1000)   
> }    
> }

Step 3: Now create a **Driver object** that **extends App** Class and inside it create instance of MultipleThreads and also create a **FixedSize Thread Pool** of size 2. use execute method to call the threads that you created.
>  private val threadPoolExecutor = Executors.newFixedThreadPool(2)     
> threadPoolExecutor.execute(multipleThreads.threadReadOne)    
> threadPoolExecutor.execute(multipleThreads.threadWriteOne)   

Step 4: Compile the code and run it.
>**sbt compile     
> sbt run**

**OUTPUT**


![Screenshot from 2023-04-05 23-50-19](https://user-images.githubusercontent.com/124979629/230375374-e982c8a3-6908-438b-81ab-dba1a40aa44f.png)
