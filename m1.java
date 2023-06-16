import java.util.concurrent.CountDownLatch;

class m1
{
    private static CountDownLatch latch = new CountDownLatch(1);
    static Thread t1 = null, t2 = null;
    public static void main(String[] args)
    {
        final Object o = new Object();
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();
        t1 = new Thread(()->
        {
            synchronized(o)//不能用this
            {
                for(char c:a1)
                {
                    System.out.print(c);
                    latch.countDown();
                    try
                    {
                        o.notify(); //叫醒等待中的某一个线程
                        o.wait(); //当前线程进入等待队列，同时释放锁，让别的非wait线程去竞争
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                o.notify(); // 结束线程
            }
        });

        t2 = new Thread(()->
        {
            try
            {
                latch.await();
            }
            catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
            synchronized(o)
            {
                for(char c:a2)
                {
                    System.out.print(c);
                    try
                    {
                        o.notify();
                        o.wait();
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        });
        t1.start();
        t2.start();
    }
}