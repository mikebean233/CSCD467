public class WaiterRunner implements Runnable {
    private Thread _printer;

    public void setPrinter(Thread printer)
    {
        if(_printer == null)
            _printer = printer;
    }

    @Override
    public void run() {
        if(_printer == null)
            throw new NullPointerException();

        while(_printer.getState() == Thread.State.NEW){
        }

        while(true)
        {
            try
            {
                Thread.currentThread().sleep(10000);
            }
            catch(InterruptedException ex)
            {
                System.out.println("Printer got his work half done!");
                break;
            }
        }
        System.out.println("Waiter has done its work, terminating");
    }
}
