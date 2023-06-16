import java.util.concurrent.Executor;

class b1 implements Executor
{
    public static void main(String[] args)
    {
        new b1().execute(()->
        {
            System.out.println("hello");
        });
    }

    @Override
    public void execute(Runnable command)
    {
        command.run();
    }
}