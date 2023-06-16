class a2 implements Runnable
{
    private int count = 10;
    public synchronized void run()
    {
        --count;
        System.out.println(Thread.currentThread().getName()+" count = "+count);
    }

    public static void main(String[] args)
    {
        a2 t = new a2(); //必须写
        for(int i = 0; i < 5; i++)
        {
            new Thread(t, "THREAD" + i).start();
        }
    }
}