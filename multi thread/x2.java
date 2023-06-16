import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;  
class x2
{
	private ReadWriteLock lock = new ReentrantReadWriteLock();  // 读多写少，提高效率
	private Lock rlock = lock.readLock();
	private Lock wlock = lock.writeLock();
	public final static int LOOP = 100;	
	private int value = 0;
	public static void main(String[] args) throws Exception
	{
		x2 m = new x2();
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
		wlock.lock();
		try
		{
			value += n;
		}
		finally
		{
			wlock.unlock();
		}
	}

	void dec(int n) 
	{
		wlock.lock();
		try
		{
			value -= n;
		}
		finally
		{
			wlock.unlock();
		}
	}

	int get()
	{
		rlock.lock();
		try
		{
			return value;
		}
		finally
		{
			rlock.unlock();
		}
	}
}