package me.saint1729.multithreading.basics;

public class BasicSumExample {

    public static void main(String[] args) throws InterruptedException {
        Sum.runTest();
    }

}

class Sum {
    long start;
    long end;
    long sum = 0;
    private static final long MAX = Integer.MAX_VALUE;

    public Sum(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public void add() {
        for (long i = start; i <= end; i++) {
            sum += i;
        }
    }

    public static void oneThread() {
        long start = System.currentTimeMillis();
        Sum s = new Sum(1, MAX);
        s.add();
        long end = System.currentTimeMillis();
        System.out.println("Sum = " + s.sum + ". Total time for one thread = " + (end - start));
    }

    public static void twoThreads() throws InterruptedException {
        long start = System.currentTimeMillis();

        Sum s1 = new Sum(1, MAX / 2);
        Sum s2 = new Sum((MAX) / 2 + 1, MAX);

        Thread t1 = new Thread(() -> {
            s1.add();
        });

        Thread t2 = new Thread(() -> {
            s2.add();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long ans = s1.sum + s2.sum;
        long end = System.currentTimeMillis();

        System.out.println("Sum = " + ans + ". Total time for two threads = " + (end - start));
    }

    public static void runTest() throws InterruptedException {
        oneThread();
        twoThreads();
    }

}
