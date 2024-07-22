package Threading;

public class OddEven {

    private static boolean printOddEven = true;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Runnable evenTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i += 2) {
                    synchronized (lock) {
                        while (!printOddEven) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("Even: " + i);
                        printOddEven = false;
                        lock.notify();
                    }
                }
            }
        };

        Runnable oddTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i += 2) {
                    synchronized (lock) {
                        while (printOddEven) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("Odd: " + i);
                        printOddEven = true;
                        lock.notify();
                    }
                }
            }
        };

        Thread evenThread = new Thread(evenTask);
        Thread oddThread = new Thread(oddTask);
        evenThread.start();
        oddThread.start();

        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
