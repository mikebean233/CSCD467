public class Monitor {
    private int _runningThreadId;
    private int _noThreads;
    public Monitor(int noThreads){
        if(noThreads < 1)
            throw new IllegalArgumentException("There must be at least one thread running");

        _noThreads = noThreads;
        _runningThreadId = 0;
    }

    public void waitForTurn(int threadId){
        while(_runningThreadId != threadId) {
            try {
                synchronized (this){
                    this.wait();
                }
            }catch(Exception e){}
        }
    }

    public synchronized void moveToNextThread(){
        ++_runningThreadId;
        _runningThreadId %= (_noThreads);
        this.notify();
    }
}