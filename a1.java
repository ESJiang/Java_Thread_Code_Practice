import java.util.concurrent.CountDownLatch;
class a1
{
    private static CountDownLatch latch = new CountDownLatch(1);
    private static int count = 10;
    public synchronized void m() //锁定new a1()
    {
        --count;
        System.out.println("count = " + count);
        latch.countDown();
    }

    public static synchronized void m1()//锁定a1.class
    {
        try
        {
            latch.await();
            --count;
            System.out.println("count = " + count);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        new Thread(()->
        {
            new a1().m();
        },"t1").start();

        new Thread(()->
        {
            a1.m1();
        },"t2").start();
    }
}