import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    // Job queue constants
    private static final int JOB_QUEUE_SIZE = 50;

    // Thread Manager
    private static final int THREAD_MANAGER_LOW_THRESHOLD = 10;
    private static final int THREAD_MANAGER_HIGH_THRESHOLD = 20;
    private static final int THREAD_MANAGER_POLL_PERIOD = 500; // milliseconds

    // Thread Pool constants
    private static final int THREAD_POOL_INITIAL_SIZE = 5;
    private static final int THREAD_POOL_MAX_SIZE = 50;

    // Log File
    private static final String LOG_FILE = "log.txt";

    // Socket
    private static final int SOCKET = 9898;


    public static void main(String[] args) throws Exception{
        //String logFileName = (args.length >0) ? args[0] : "";
        ServerSocket listener = new ServerSocket(SOCKET);
        ServerLogger serverLogger = new ServerLogger(LOG_FILE, true);  //logFileName, (args.length > 0) && true);
        serverLogger.appendToLog("starting server...");
        JobQueue jobQueue = new JobQueue(JOB_QUEUE_SIZE, serverLogger);
        ThreadPool threadPool = new ThreadPool(THREAD_POOL_INITIAL_SIZE, THREAD_POOL_MAX_SIZE, jobQueue, serverLogger, listener);
        ThreadManager threadManager = new ThreadManager(threadPool, jobQueue,
                THREAD_MANAGER_LOW_THRESHOLD,
                THREAD_MANAGER_HIGH_THRESHOLD,
                THREAD_MANAGER_POLL_PERIOD,
                serverLogger);


        try{
            while (true) {
                Socket clientSocket = listener.accept();
                serverLogger.appendToLog("connected to client: " + getClientIdentifier(clientSocket));
                if(!jobQueue.addJob(clientSocket)){
                    String message = " Unable to add job from client " + clientSocket + " to job queue (the job queue is full)";
                    message += ", closing client connection...";
                    serverLogger.appendToLog(message);
                    try{clientSocket.close();}catch(Exception e){}
                }
            }
        }
        catch(Exception e){
            serverLogger.appendToLog("exception \"" + e.getMessage() + "\"received in server main thread, closing server slocket...");
            try{listener.close();}catch(Exception ex){}
        }
        threadManager.interrupt();
        threadManager.join();
        serverLogger.appendToLog("terminating server...");
        serverLogger.close();
    }

    private static String getClientIdentifier(Socket clientSocket){
        if(clientSocket == null)
            System.out.println("null");

        return clientSocket.getInetAddress() + ":" + clientSocket.getPort();
    }

}