package ru.bernarsoft.innopolis;


public class Main {

    private static final Object lock = new Object();
    private static final int SESSION_TIME = 50;
    private static int count = 0;


    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                while (count < SESSION_TIME) {
                    synchronized (lock) {
                        lock.notifyAll();
                    }
                    try {
                        Thread.sleep(1000);
                        count++;
                        System.out.println("Session time is " + count + " seconds");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

        Thread thread5 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (lock) {
                        try {
                            lock.wait();
                            if (count % 5 == 0) {
                                System.out.println("Every 5 seconds");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    };
                }
            }
        });

        Thread thread7 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (lock) {
                        try {
                            lock.wait();
                            if (count % 7 == 0) {
                                System.out.println("Every 7 seconds");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    };
                }
            }
        });

        thread1.start();
        thread5.start();
        thread7.start();
    }
}
