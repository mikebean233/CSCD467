import java.net.Socket;

public class JobQueue {
    private SharedQueue<Socket> _jobQueue;
    private int _jobQueueSize;
    private Object _addJobLock, _fetchJobLock;
    private ServerLogger _serverLogger;

    public JobQueue(int jobQueueSize, ServerLogger serverLogger){

        if(jobQueueSize < 1)
            throw new IllegalArgumentException("Job Queue size must be at least one: " + jobQueueSize);
        if(serverLogger == null)
            throw new IllegalArgumentException();
        _serverLogger = serverLogger;
        _jobQueueSize = jobQueueSize;
        _addJobLock = new Object();
        _fetchJobLock = new Object();
        _jobQueue = new SharedQueue<>();
        _serverLogger.appendToLogNoInterrupt("creating job queue of size " + jobQueueSize);
    }

    public boolean addJob(Socket thisJob){
        if(thisJob == null)
            throw new IllegalArgumentException();
        _serverLogger.appendToLogNoInterrupt("adding job to jobQueue: " + thisJob);
        synchronized (_addJobLock){
            // If there is no more room in the job queue, let the caller know (return false);
            if(_jobQueue.length() == _jobQueueSize)
                return false;

            _jobQueue.enqueue(thisJob);
            synchronized (_fetchJobLock) {
                _fetchJobLock.notifyAll();
            }
        }
        _serverLogger.appendToLogNoInterrupt("there are now " + _jobQueue.length() + " jobs in the queue");
        return true;

    }

    public Socket fetchJob() throws InterruptedException{
        synchronized (_fetchJobLock) {

            while(!_jobQueue.any())
                _fetchJobLock.wait();

            _serverLogger.appendToLog("there are now " + _jobQueue.length() + " jobs in the queue");
            return _jobQueue.dequeue();
        }
    }


    public int maxSize(){return _jobQueueSize;}
    public boolean Any(){return _jobQueue.any();}
    public int length(){ return _jobQueue.length();}
}
