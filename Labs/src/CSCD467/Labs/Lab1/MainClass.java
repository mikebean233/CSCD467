package CSCD467.Labs.Lab1;

public class MainClass {
	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		Thread mainWindowThread = new Thread(new MainWindowRunner(mainWindow));
		mainWindowThread.start();
		
		// wait for the mainWindow thread to start
		while(mainWindowThread.getState() != Thread.State.RUNNABLE){}
		
		
	}
}
