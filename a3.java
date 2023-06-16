//在同步方法中能不能调用非同步方法？ 可以
class a3
{
    synchronized void m1()
    {
        System.out.println("m1 start");
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("m1 end");
    }
    void m2()
    {
        System.out.println("m2 start");
        try
        {
            Thread.sleep(500);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("m2 end");
    }

    public static void main(String[] args)
    {
        //new Thread(new a3()::m1,"t1").start();
        //new Thread(new a3()::m2,"t2").start();
        new Thread(()->
        {
            new a3().m1();
        }
        ,"t1").start();
        new Thread(()->
        {
            new a3().m2();
        }
        ,"t2").start();
    }
}