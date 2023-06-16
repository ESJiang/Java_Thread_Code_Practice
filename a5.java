//volatile 关键字-》一个变量在多个线程可见，在这个值变化后强制其他线程刷新。
//volatile 比 synchronized 效率高,但是在执行++n(非原子性操作或多个atomic操作)时不能保证原子性。
class a5
{
    volatile boolean running = true;

    void m()
    {
        System.out.println("m start");
        while(running) //死循环
        {

        }
        System.out.println("m end");
    }

    public static void main(String[] args)
    {
        a5 t = new a5(); //防止new a5()调用完就销毁 //new Thread(()->t.m()).start();
        new Thread(t::m).start();
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        t.running = false;
    }
}