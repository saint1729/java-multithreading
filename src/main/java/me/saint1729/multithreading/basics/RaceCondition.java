package me.saint1729.multithreading.basics;

import java.util.Random;

public class RaceCondition {

    public static void main(String[] args) throws InterruptedException {
        RaceConditionDemo.runTest();
    }

}


class RaceConditionDemo {

    int randInt;
    Random random = new Random();

    public void printer() {
        int i = 1000000;
        while (i > 0) {
            if ((randInt % 5) == 0) {
                System.out.println("before = " + randInt);
                if ((randInt % 5) != 0) {
                    System.out.println("after = " + randInt);
                }
            }
            i -= 1;
        }
    }

    public void modifier() {
        int i = 1000000;
        while (i > 0) {
            randInt = random.nextInt(1000);
            i -= 1;
        }
    }

    public static void runTest() throws InterruptedException {

        RaceConditionDemo raceConditionDemo = new RaceConditionDemo();

        Thread t1 = new Thread(raceConditionDemo::printer);

        Thread t2 = new Thread(raceConditionDemo::modifier);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
