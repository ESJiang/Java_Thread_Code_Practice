import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
class a7
{
    ArrayList<Object> list = new ArrayList<Object>();

    private void add(Object o)
    {
        list.add(o);
    }

    private int size()
    {
        return list.size();
    }

    public static void main(String[] args)
    {
        CountDownLatch latch = new CountDownLatch(1);
        a7 m = new a7();
        new Thread(()->
        {
            for(int i = 0; i<10;i++)
            {
                m.add(new Object());
                System.out.println("add "+i);
                if(m.size() == 5)
                {
                    latch.countDown();
                }
                try
                {
                    Thread.sleep(100);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
        new Thread(()->
        {
            if(m.size() != 5)
            {
                try
                {
                    latch.await();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 end");
        }, "t2").start();
    }
}