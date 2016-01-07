package CSCD467.Labs.Lab1;

public class MainWindowRunner implements Runnable{
	private MainWindow _thisWindow;
	
	public MainWindowRunner(MainWindow thisWindow)
	{
		_thisWindow = (thisWindow == null) ? new MainWindow() : thisWindow;
	}
	
	@Override
	public void run() {
		try {
			_thisWindow.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
