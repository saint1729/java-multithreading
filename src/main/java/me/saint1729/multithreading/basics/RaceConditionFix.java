package me.saint1729.multithreading.basics;

import java.util.Random;

public class RaceConditionFix {

    public static void main(String[] args) throws InterruptedException {
        RaceConditionDemo2.runTest();
    }

}


class RaceConditionDemo2 {

    int randInt;
    Random random = new Random();

    public void printer() {
        int i = 1000000;
        while (i > 0) {
            synchronized (this) {
                if ((randInt % 5) == 0) {
//                    System.out.println("before = " + randInt);
                    if ((randInt % 5) != 0) {
                        System.out.println("after = " + randInt);
                    }
                }
            }
            i -= 1;
        }
    }


    public void modifier() {
        int i = 1000000;
        while (i > 0) {
            synchronized (this) {
                randInt = random.nextInt(1000);
                i -= 1;
            }
        }
    }

    public static void runTest() throws InterruptedException {

        RaceConditionDemo2 raceConditionDemo2 = new RaceConditionDemo2();

        Thread t1 = new Thread(raceConditionDemo2::printer);

        Thread t2 = new Thread(raceConditionDemo2::modifier);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
