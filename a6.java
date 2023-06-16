//既然volatile不能应对非原子操作，那么可不可以不使用synchronized处理呢？
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
class a6
{
    AtomicInteger count = new AtomicInteger(0);
    void m()
    {
        for (int i = 0; i<10; i++)
        {
            count.incrementAndGet(); //但只能有一个不能多个atomic方法在同个for循环里
        }
    }

    public static void main(String[] args)
    {
        a6 t = new a6();
        ArrayList<Thread> threads = new ArrayList<Thread>();
        for (int i = 0;i < 10 ; i++)
        {
            threads.add(new Thread(t::m));
        }
        threads.forEach((o)->o.start());
        threads.forEach((o)->
        {
            try
            {
                o.join();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}