import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class a10
{
    static Queue<String> tickets = new ConcurrentLinkedQueue<String>();

    static
    {
        for (int i = 0; i<10; i++)
        {
            tickets.add("编号 "+i);
        }
    }
    public static void main(String[] args)
    {
        for (int i = 0;i < 10 ; i++)
        {
            new Thread(()->
            {
                while(true)
                {
                    String s = tickets.poll();
                    if(s == null)
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("销售了———" +s);
                    }
                }
            }).start();
        }
    }
}