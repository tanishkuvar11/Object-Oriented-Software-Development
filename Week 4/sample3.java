class sample3 {
    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task1());
        Thread t2 = new Thread(new Task2());

        t1.start();
        t2.start();
    }
}

class Task1 implements Runnable {

    public void run() {
        synchronized (sample3.lock1) {
            System.out.println("Thread 1: Holding lock1");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (sample3.lock1) {
                System.out.println("Thread 1: Holding lock2");
            }
        }
    }
}

class Task2 implements Runnable {

    public void run() {
        synchronized (sample3.lock2) {
            System.out.println("Thread 2: Holding lock2");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (sample3.lock1) {
                System.out.println("Thread 2: Holding lock1");
            }
        }
    }
}
