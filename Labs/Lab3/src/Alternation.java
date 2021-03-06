public class Alternation {
	
	boolean isT1turn;
	final Object lock = new Object();
	Thread t1;
	Thread t2;
	private Monitor monitor;
	public Alternation() {
		monitor = new Monitor();
		isT1turn = true;
		t1 = new Thread(new Runnable() {
	        @Override
	        public void run() {
                for (int i = 1; i <= 50; i += 2) {
					while(!monitor.getIsT1Turn());
					System.out.println("Message " + i + " from Thread T1");
					isT1turn = false;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					monitor.setT2Turn();
				}// end for
	        }
	    });
	    t2 = new Thread(new Runnable() {

	        @Override
	        public void run() {
                for (int i = 2; i <= 50; i += 2) {
                	while(monitor.getIsT1Turn());
					System.out.println("Message " + i + " from Thread T2");
					isT1turn = true;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					monitor.setT1Turn();
				}// end for
	        }
	    });
	    t1.start();
	    t2.start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Alternation();
	}
}


