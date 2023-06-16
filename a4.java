import java.util.concurrent.TimeUnit;
class a4
{
    private double balance;

    synchronized void set(String name, double balance)
    {
        try
        {
            Thread.sleep(10);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    synchronized double getbalance(String name)
    {
        return this.balance;
    }

    public static void main(String[] args)
    {
        a4 a = new a4();
        new Thread(()->a.set("jiang",2000),"t2").start();

        try
        {
            TimeUnit.SECONDS.sleep(1);
        }

        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println(a.getbalance("jiang"));

        try
        {
            TimeUnit.SECONDS.sleep(2);
        }

        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println(a.getbalance("jiang"));
    }
}