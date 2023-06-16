import java.util.concurrent.locks.ReentrantLock; 
class x1
{
	private ReentrantLock lock = new ReentrantLock(); 
	private int value = 0;
	final static int LOOP = 100;	
	public static void main(String[] args) throws Exception
	{
		x1 m = new x1();
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

	void add(int n)
	{
		lock.lock();
		try
		{
			value += n;
		}
		finally
		{
			lock.unlock();
		}
	}

	void dec(int n)
	{
		lock.lock();
		try
		{
			value -= n;
		}
		finally
		{
			lock.unlock();
		}
	}

	int get()
	{
		lock.lock();
		try
		{
			return value;
		}
		finally
		{
			lock.unlock();
		}
	}
}