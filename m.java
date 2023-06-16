import java.util.concurrent.locks.LockSupport;
// aqs的核心方法 摆脱notify前必须要wait的缺点
class m
{
    private static Thread t1 = null, t2 = null;
    public static void main(String[] args) throws Exception
    {
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();
        t1 = new Thread(()->
        {
            for(char c: a1)
            {
                System.out.print(c);
                LockSupport.unpark(t2); // 叫醒t2
                LockSupport.park();  // t1堵塞
            }
        }, "thread1");

        t2 = new Thread(()->
        {
            for(char c: a2)
            {
                LockSupport.park(); // t2堵塞
                System.out.print(c);
                LockSupport.unpark(t1); // 叫醒t1
            }
        }, "thread2");

        t1.start();
        t2.start();
    }
}