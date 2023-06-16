 //import java.util.concurrent.atomic.AtomicInteger;
class x3
{
	private final static int LOOP = 100;	
	//private AtomicInteger value = new AtomicInteger(0);
	private int value = 0;
	public synchronized void add(int m)
	{
		//return this.value.addAndGet(m);
		value += m;
	}

	public synchronized void dec(int m)
	{
		//return this.value.addAndGet(-m);
		value -= m;
	}

	public int get()
	{
		//return value.get();
		return value;
	}

	public static void main(String[] args) throws Exception
	{
		x3 m = new x3();
		Thread t1 = new Thread()
		{
			public void run()
			{
				for(int i = 0; i < LOOP; i++)
				{
					m.add(1);
				}
			}
		};
		Thread t2 = new Thread()
		{
			public void run()
			{
				for(int i = 0 ;i < LOOP; i++)
				{
					m.dec(1);
				}
			}
		};
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(m.get()); 
	}
}