public class SecondaryThreadRunner implements Runnable
{
	private MainWindow _thisWindow;
	public SecondaryThreadRunner(MainWindow thisWindow)
	{
		if(thisWindow == null)
			throw new NullPointerException("SecondaryThreadRunner received a null reference in its constructor");
		_thisWindow = thisWindow;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run()
	{
		try
		{
			while(true)
			{
				Thread.currentThread().sleep(1000);
				_thisWindow.appendToTextArea("Message from Thread ---> " + Thread.currentThread().getName());
			}
		}
		catch(InterruptedException e)
		{
			_thisWindow.appendToTextArea(Thread.currentThread().getName() + " Gets Interrupted! Terminate!");
		}
		
		
	}
	

}
