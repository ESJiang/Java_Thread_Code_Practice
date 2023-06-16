//threadlocal的使用
class a9
{
    public static void main(String[] args)
    {
        class person
        {
            String name = "zhangsan";
            person(String name)
            {
                this.name = name;
            }
        }
        ThreadLocal<person> y = new ThreadLocal<person>();
        new Thread(()->
        {
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println(y.get());
        }).start();

        new Thread(()->
        {
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            y.set(new person("jiang"));
            System.out.println(y.get().name);
        }).start();
    }
}