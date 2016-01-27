import java.util.AbstractList;
import java.util.ArrayList;

public class ThreadPrime extends Thread {
	private int low;
	private int high;

	private Counter c;

	// each thread only  takes care of one subrange (low, high)
	public ThreadPrime (int lowLocal, int highLocal, Counter ct) {
		this.low = lowLocal;
		this.high = highLocal;
		c = ct;
	}

	private boolean isPrime(int n) {
		//check if n is a multiple of 2
		if (n%2==0) return false;
		//if not, then just check the odds
		for(int i=3;i*i<=n;i+=2) {
			if(n%i==0)
				return false;
		}
		return true;
	}

	public void run(){

		int curPrimeCount = 0;
		for(int curNumber = high; curNumber >= low; --curNumber){
			if(isPrime(curNumber))
				++curPrimeCount;
		}
		c.increment(curPrimeCount);
	}
}
