public class Main {
	public static void main(String[] args) {
		PrinterRunner printerRunner = new PrinterRunner();
		WaiterRunner waiterRunner = new WaiterRunner();
		Thread printer = new Thread(printerRunner);
		Thread waiter  = new Thread(waiterRunner);
		printerRunner.setWaiter(waiter);
		waiterRunner.setPrinter(printer);
		printer.start();
		waiter.start();

		try {
			printer.join();
			waiter.join();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		System.out.println("Both Waiter and Printer have finished their work!");
	}
}