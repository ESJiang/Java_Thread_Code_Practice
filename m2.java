//wait和while使用 wait释放锁，notify不释放锁
//notify和wait必须得到对象。
//一般使用notifyall
class m2
{
    private static volatile boolean t1started = false;
    static Thread t1 = null, t2 = null;
    public static void main(String[] args)
    {
        final Object o = new Object();
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();
        t1 = new Thread(()->
        {
            synchronized(o)
            {
                while(!t1started )
                {
                    for(char c:a1)
                    {
                        System.out.print(c);
                        try
                        {
                            t1started = true;
                            o.notify();
                            o.wait();
                        }
                        catch(InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    o.notify(); // 结束线程
                }}
            });

        t2 = new Thread(()->
        {
            synchronized(o)
            {
                while(!t1started)
                {
                    try
                    {
                        o.wait();
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                }

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
                t1started = false;
                o.notify();
            }
        });
        t1.start();
        t2.start();
    }
}