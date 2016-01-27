import java.util.ArrayList;

public class MyPrimeTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		if (args.length < 3) {
			System.out.println("Usage: MyPrimeTest numThread low high \n");
			return;
		}
		int nthreads = Integer.parseInt(args[0]);
		int low = Integer.parseInt(args[1]);
		int high = Integer.parseInt(args[2]);
		Counter c = new Counter();
		
		//test cost of serial code
		long start = System.currentTimeMillis();
		int numPrimeSerial = SerialPrime.numSerailPrimes(low, high);
		long end = System.currentTimeMillis();
		long timeCostSer = end - start;
		System.out.println("Time cost of serial code: " + timeCostSer + " ms.");
		
		//test of concurrent code
		// **************************************
        // Write me here
		ArrayList<Thread> threadPool = new ArrayList<>();
		int threadWorkSize = (high - low + 1) / nthreads;
		int threadLow = 0, threadHigh;
		for(int i = 1; i <= nthreads; ++i) {
			threadLow = (i == 1) ? low : threadLow +  threadWorkSize;
			threadHigh = (i == nthreads) ? high : threadLow + threadWorkSize - 1;
			threadPool.add(new ThreadPrime(threadLow, threadHigh, c));
		}
		start = System.currentTimeMillis();

		// start all of the threads
		for(Thread thisThread : threadPool)
			thisThread.start();
		// hold on until all of the threads are finished
		for(Thread thisThread : threadPool)
			thisThread.join();

		end = System.currentTimeMillis();
		long timeCostCon = end - start;
		// **************************************

		System.out.println("Time cost of parallel code: " + timeCostCon + " ms.");
		System.out.format("The speedup ration is by using concurrent programming: %5.2f. %n", (double)timeCostSer / timeCostCon);
		
		System.out.println("Number prime found by serial code is: " + numPrimeSerial);
		System.out.println("Number prime found by parallel code is " + c.total());
	}
		

}
