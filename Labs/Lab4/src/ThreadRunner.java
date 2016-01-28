public class ThreadRunner implements Runnable{
    private Monitor _monitor;
    private int _threadId;

    public ThreadRunner(int threadId, Monitor monitor){
        _threadId = threadId;
        _monitor = monitor;
    }
    @Override
    public void run() {
        for(int i = 1; i <= 10; ++i){
            _monitor.waitForTurn(_threadId);
            System.out.println(generateOutputString(i));
            _monitor.moveToNextThread();
        }

    }

    private String generateOutputString(int messageNo){
        String prefix = (messageNo == 1) ? "1st" :
                        (messageNo == 2) ? "2nd" :
                        (messageNo == 3) ? "3rd" :
                        messageNo + "th";
        return prefix + " Message from thread " + _threadId;
    }
}
