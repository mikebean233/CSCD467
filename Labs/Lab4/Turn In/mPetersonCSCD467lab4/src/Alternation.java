import java.util.ArrayList;

public class Alternation {
	private Monitor monitor;

	public Alternation(int noThreads) {
		monitor = new Monitor(noThreads);
		ArrayList<Thread> threadPool = new ArrayList<>();

		for(int thisThreadId = 0; thisThreadId < noThreads; ++thisThreadId){
			threadPool.add(new Thread(new ThreadRunner(thisThreadId, monitor)));
		}

		for(Thread thisThread : threadPool)
			thisThread.start();

		try {
			for (Thread thisThread : threadPool)
				thisThread.join();
		}
		catch(Exception e){}
	}

	public static void main(String[] args) {
		if(args.length != 1)
			usage();

		int noThreads = 0;
		try {
			noThreads = Integer.parseInt(args[0]);
		}
		catch(Exception e){
			System.out.println(args[0] + " is not a valid integer");
			usage();
		}
		new Alternation(noThreads);
	}

	public static void usage(){
		System.out.println("usage: java Alternation noThreads");
		System.exit(0);
	}
}


