public class TextRepeater implements Runnable {
	private MainWindow _thisWindow;
	public TextRepeater(MainWindow thisWindow){
		if(thisWindow == null)
			throw new NullPointerException("TextRepeater received a null reference in its constructor");
		_thisWindow = thisWindow;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		try{
			while(true){
				Thread.currentThread().sleep(1000);
				_thisWindow.appendToTextArea(Thread.currentThread().getName());
			}
		}
		catch(InterruptedException e){
			return;
		}
	}
}
