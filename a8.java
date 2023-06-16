//reetrantlock 代替 synchronized
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

class a8
{
    Lock lock = new ReentrantLock();
    void m1()
    {
        try
        {
            lock.lock();
            for (int i = 0; i < 10; i++)
            {
                Thread.sleep(100);
                System.out.println(i);
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            lock.unlock();
        }
    }
    void m2()
    {
        lock.lock();
        System.out.println("m2 is running");
        lock.unlock();
    }
    public static void main(String[] args)
    {
        a8 t = new a8();
        new Thread(t::m1).start();
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        new Thread(t::m2).start();
    }
}