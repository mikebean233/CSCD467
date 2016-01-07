package CSCD467.Labs.Lab1;

public class SecondaryThreadRunner implements Runnable
{

	@Override
	public void run()
	{
		while(true)
		{
			Thread.currentThread().sleep(1000);
			
		}
	}
	

}
