import java.awt.*;

class Philosopher extends Thread {
  private int identity;
  private PhilCanvas view;
  private Diners controller;
  private Fork left, right, firstFork, secondFork;

  Philosopher(Diners ctr, int id, Fork l, Fork r) {
    controller = ctr; view = ctr.display;
    identity = id; left = l; right = r;

    boolean leftForkIsFirst = left.getIdentity() < right.getIdentity();
    firstFork  = (leftForkIsFirst) ? left  : right;
    secondFork = (leftForkIsFirst) ? right : left;
  }

  public void run() {
    int remainingTries = 3;
    boolean haveFirstFork, haveSecondFork;

    try {
      while (true) {
        //thinking
        view.setPhil(identity,view.THINKING);
        sleep(controller.thinkingTime());

        //hungry
        view.setPhil(identity,view.HUNGRY);
        sleep(controller.hungryTime());

        //get forks
        haveFirstFork = haveSecondFork = false;
        remainingTries = 3;

        if(firstFork.get()){
          view.setPhil(identity, (firstFork == left) ? view.GOTLEFT : view.GOTRIGHT);
          haveFirstFork = true;
          while (remainingTries-- > 0) {
            if (secondFork.get()) {
              haveSecondFork = true;
              break;
            } else
              synchronized (secondFork) {
                secondFork.wait(controller.holdOneForkTime());
              }
          }

          if(!haveSecondFork) {
            synchronized (firstFork){
              firstFork.put();
              firstFork.notifyAll();
            }
          }

        }

        if(haveFirstFork && haveSecondFork) {
          //eating
          view.setPhil(identity, view.EATING);
          sleep(controller.eatTime());

          synchronized (firstFork) {
            view.setPhil(identity, (firstFork == right) ? view.GOTLEFT : view.GOTRIGHT);
            firstFork.put();
            firstFork.notifyAll();
          }
          synchronized (secondFork){
            secondFork.put();
            secondFork.notifyAll();
          }
        }

      }
    } catch (java.lang.InterruptedException e) {}
  }
}
