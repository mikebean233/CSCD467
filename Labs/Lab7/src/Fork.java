import java.awt.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Fork {

  private PhilCanvas display;
  private int identity;
  private static int heldForkCount = 0;
  private static Object heldCountLock = new Object();
  private Lock lock;

  Fork(PhilCanvas disp, int id){
    display = disp;
    identity = id;
    lock = new ReentrantLock();
}

  public void put() {
    lock.unlock();
    display.setFork(identity,false);

    synchronized (heldCountLock){
      heldForkCount++;
    }
  }

  public int getIdentity(){return identity;}

  public boolean get() throws java.lang.InterruptedException {
    boolean result;
    if((result = lock.tryLock()))
      display.setFork(identity,true);

    synchronized (heldCountLock){
      heldForkCount--;
    }
    return result;
  }

  public static int getHeldForkCount(){
    synchronized (heldCountLock){
      return heldForkCount;
    }
  }

}
