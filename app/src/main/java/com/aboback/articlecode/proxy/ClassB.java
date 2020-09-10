package com.aboback.articlecode.proxy;

/**
 * @author jhb
 * @date 2020/9/10
 */
public class ClassB {
    private ClassA classA;

    public ClassB(ClassA classA) {
        this.classA = classA;
    }

    public void method1() {
        classA.method1();
    }

    public void method2() {
        classA.method2();

    }
}
