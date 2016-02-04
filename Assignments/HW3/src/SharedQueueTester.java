import java.util.*;
public final class SharedQueueTester {
    public static void main(String[] args) {
        SharedQueue<String> queue = new SharedQueue<>(500);

        // thread 1
        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    char thisChar = (char) ('a' + (new Random()).nextInt(26));
                    queue.enqueue("" + thisChar);
                    System.out.println("enqueue: " + thisChar);
                }
            }
        })).start();

        // thread 1
        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("dequeue: " + queue.dequeue());
                }
            }
        })).start();
    }
}
