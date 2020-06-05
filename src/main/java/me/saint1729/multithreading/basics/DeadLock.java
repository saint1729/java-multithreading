package me.saint1729.multithreading.basics;

public class DeadLock {

    public static void main(String[] args) throws InterruptedException {
        DeadLockDemo.testDeadlock();
    }

}

class DeadLockDemo {

    int counter = 0;
    static int MAX = 1;

    final Object lock1 = new Object();
    final Object lock2 = new Object();

    public void increment() throws InterruptedException {

        synchronized (lock1) {
            System.out.println("Acquired lock1 from increment");
//            Thread.sleep(10);
            synchronized (lock2) {
                System.out.println("Acquired lock2 from increment");
                counter += 1;
            }
        }

    }

    public void decrement() throws InterruptedException {

//        Thread.sleep(10);
        synchronized (lock2) {
            System.out.println("Acquired lock2 from decrement");
//            Thread.sleep(10);
            synchronized (lock1) {
                System.out.println("Acquired lock1 from decrement");
                counter -= 1;
            }
        }

    }

    public static void testDeadlock() throws InterruptedException {
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < MAX; i++) {
                try {
                    deadLockDemo.increment();
                    System.out.println("After increment method with i = " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < MAX; i++) {
                try {
                    deadLockDemo.decrement();
                    System.out.println("After decrement method with i = " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }

}
