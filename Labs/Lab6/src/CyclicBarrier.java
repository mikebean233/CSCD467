//import java.util.concurrent.BrokenBarrierException;
public class CyclicBarrier {
    private int _noThreads, _remainingThreadsInCycle, _currentCycleIndex;
    private Runnable _lastCommand;
    private boolean _brokenBarrier;


    public CyclicBarrier(int noThreads, Runnable lastCommand) throws IllegalArgumentException{
        if(noThreads < 1 || lastCommand == null)
            throw new IllegalArgumentException();

        _currentCycleIndex = 0;
        _lastCommand = lastCommand;
        _noThreads = noThreads;
        _remainingThreadsInCycle = noThreads;
        _brokenBarrier = false;
    }

    public synchronized int await() throws BrokenBarrierException, InterruptedException{
        // Store the current cycle index into this stack frame
        int thisCycleIndex = _currentCycleIndex;
        int thisThreadPlacement = _remainingThreadsInCycle - 1;
        --_remainingThreadsInCycle;

        if(_brokenBarrier) {
            this.notifyAll();
            throw new BrokenBarrierException();
        }
        if(Thread.currentThread().isInterrupted()) {
            _brokenBarrier = true;
            this.notifyAll();
            throw new InterruptedException();
        }

        // This thread is the last to finish this cycle
        if(_remainingThreadsInCycle == 0)
        {   // reset the waiting and running counts
            _remainingThreadsInCycle = _noThreads;
            _lastCommand.run();
            ++_currentCycleIndex;
            this.notifyAll();
            return thisThreadPlacement;
        }

        while(thisCycleIndex == _currentCycleIndex){
            // If this thread is part of the current cycle, keep waiting.
            try {
                this.wait();
                if(_brokenBarrier)
                    throw new BrokenBarrierException();
            }
            catch(BrokenBarrierException brokenBarrierException){
                this.notifyAll();
                throw brokenBarrierException;
            }
            catch(InterruptedException interruptedException){
                if(!_brokenBarrier) {
                    _brokenBarrier = true;
                    throw new BrokenBarrierException(interruptedException.getMessage());
                }
                throw interruptedException;
            }
        }
    return thisThreadPlacement;
    }


}
