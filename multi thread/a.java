class a implements Runnable
{
	volatile boolean running = true; //中断线程，数据立即更新到主内存 
	public void run() 
	{
		while(running)
		{
			System.out.println("子线程");
			try
			{
				Thread.sleep(100); // 体现主线程在等到线程对象结束。
			}
			catch(InterruptedException e)
			{
				System.out.println("interrupted");
				break;
			}
		}
		System.out.println("子线程结束");
	}

	public static void main(String[] args) throws Exception
	{
		a m = new a();  // 必须建立对象，不能用new t1(), 因为要传递变量running需要明确的实例。
		Thread t1 = new Thread(m);
		//t1.setDaemon(true); // 必须在start()前写，main结束后该线程强制结束。
		t1.start();
		//t1.join(); // 等待线程t1执行完在进行其他线程。
		Thread.sleep(1000);
		m.running = false;
		t1.join();
		System.out.println("主线程");
	}
}
