package me.saint1729.multithreading.basics;

public class NonReentrantLock {


    public static void main(String[] args) throws InterruptedException {

        NonReentrantLockDemo nonReentrantLockDemo = new NonReentrantLockDemo();

        nonReentrantLockDemo.lock();
        System.out.println("Acquired lock for first time");

        System.out.println("Before acquiring lock for second time");
        nonReentrantLockDemo.lock();
        System.out.println("Acquired lock for second time");

    }


}

class NonReentrantLockDemo {

    boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }

}
