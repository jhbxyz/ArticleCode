package com.aboback.articlecode.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author jhb
 * @date 2020/9/7
 */
public class ThreadDemo {

    public static void main(String[] args) {
        thread();
        runnable();
        futureTask();

        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.notify();
    }

    private static void futureTask() {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Anything";
            }
        });

        //通过 THread 调用
        Thread thread = new Thread(task);
        thread.start();

        //通过线程池调用
        Executors.newCachedThreadPool().submit(task);

        try {
            String result = task.get();
            System.out.println("result" + result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void runnable() {
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();
    }

    private static void thread() {
        new MyThread().start();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            // do something
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            // do something
        }
    }
}
