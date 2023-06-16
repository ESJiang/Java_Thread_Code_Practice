import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;

class m3
{
    public static void main(String[] args)
    {
        CountDownLatch latch = new CountDownLatch(1);
        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();//代表线程1
        Condition c2 = lock.newCondition();//代表线程2
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();
        new Thread(()->
        {
            try
            {
                lock.lock();
                for(char c:a1)
                {
                    System.out.print(c);
                    latch.countDown();
                    c2.signal(); //叫醒c2await下的线程
                    c1.await(); //自身在c1条件下等待
                }
                c2.signal();//叫醒c2await下的线程
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            finally
            {
                lock.unlock();
            }
        },"thread1").start();

        new Thread(()->
        {
            try
            {
                latch.await();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            try
            {
                lock.lock();
                for(char c:a2)
                {
                    System.out.print(c);
                    c1.signal(); //叫醒c2条件上await的线程
                    c2.await(); //让自身成为c2条件下await
                }
                c1.signal();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            finally
            {
                lock.unlock();
            }
        },"thread2").start();
    }
}