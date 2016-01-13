public class PrinterRunner implements Runnable{
    private Thread _waiter;

    public void setWaiter(Thread waiter)
    {
        if(_waiter == null)
            _waiter = waiter;
    }
    @Override
    public void run() {
        if(_waiter == null)
            throw new NullPointerException();

        while (_waiter.getState() == Thread.State.NEW) {
        }

        for (int i = 1; i <= 50; ++i) {
            System.out.println("Message i = " + i + ", from Thread Printer");
            if (i == 25)
                _waiter.interrupt();
        }
    }
}
