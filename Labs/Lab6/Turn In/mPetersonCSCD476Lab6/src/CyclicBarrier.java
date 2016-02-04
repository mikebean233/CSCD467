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
        // Store the current cycle index of this thread / stack frame
        int thisCycleIndex = _currentCycleIndex;
        int thisThreadRank = _remainingThreadsInCycle - 1;
        --_remainingThreadsInCycle;

        // If the barrier is broken, wake all of the threads and throw a BrokenBarrier exception
        if(_brokenBarrier) {
            this.notifyAll();
            throw new BrokenBarrierException();
        }

        // If the current thread is being interrupted, clear the interrupted flag, make the barrier broken, and throw
        // a new InterruptedException
        if(Thread.currentThread().interrupted()) {
            _brokenBarrier = true;
            this.notifyAll();
            throw new InterruptedException();
        }

        // This thread is the last to finish this cycle (trip)
        if(_remainingThreadsInCycle == 0){
            _remainingThreadsInCycle = _noThreads;
            _lastCommand.run();
            ++_currentCycleIndex;
            this.notifyAll();
            return thisThreadRank;
        }

        while(thisCycleIndex == _currentCycleIndex){
            try { // If this thread is part of the current cycle, keep waiting.
                this.wait();
                if(_brokenBarrier)
                    throw new BrokenBarrierException();
            }//      If this thread received a brokenBarrierException, wake up all of the other threads propagate it.
            catch(BrokenBarrierException brokenBarrierException){
                this.notifyAll();
                throw brokenBarrierException;
            }
            catch(InterruptedException interruptedException){
                //  If this thread received an InterruptedException and the barrier isn't broken, make the state broken,
                //  and propagate the exception
                if(!_brokenBarrier) {
                    _brokenBarrier = true;
                    throw interruptedException;
                }
                // Otherwise, throw a broken exception (the barrier is already broken)
                throw new BrokenBarrierException(interruptedException.getMessage());
            }
        }
        return thisThreadRank;
    }


}
