package com.aboback.articlecode.thread;

/**
 * @author jhb
 * @date 2020/9/7
 */
public class SynDemo {

    public static void main(String[] args) {

    }

    /**
     * 同步方法
     */
    private synchronized void sys1() {
        //锁对象为当前类对象 this
    }

    /**
     * 同步代码块
     */
    private void sys2() {
        //锁对象为 synchronized 块上的参数
        synchronized (this) {

        }
    }

    /**
     * 静态同步方法
     */
    public static synchronized void sys3() {
        //锁对象为类的Class对象
    }

}
