//并发量小：hashtable
//并发量大：concurrenthashmap
//并发量大且排序：currentskiplistmap
//不需要同步：arraylist linkedlist copyonwritelist 读的内容多写的内容少
//transferqueue synchronousqueue
import java.util.concurrent.SynchronousQueue;

class a11
{
    public static void main(String[] args) throws InterruptedException
    {
        SynchronousQueue<String> strs = new SynchronousQueue<String>();
        new Thread(()->
        {
            try
            {
                System.out.println(strs.take());
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }).start();
        strs.put("aaa");
        //strs.add("aaa"); //报错
        System.out.println(strs.size());
    }
}