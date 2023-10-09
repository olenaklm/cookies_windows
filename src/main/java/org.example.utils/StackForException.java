package org.example.utils;

public class StackForException {
    public static void main(String[] args) {
        method1();
    }
    public static void method1() {
        method2();
    }
    public static void method2() {
        method3();
//        method4();
    }
    public static void method3() {
        StackTraceElement[] stackTraceElements =
                Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTraceElements) {
            System.out.println(element.getMethodName());
        }
    }

    public static void method4() {
        int a = 3 / 0;
    }
}
