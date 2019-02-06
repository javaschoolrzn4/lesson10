package ru.rzn.sbt.javaschool.lesson9;


class FirstClass {

    synchronized void foo(SecondClass b) {

        String name = Thread.currentThread().getName();
        System.out.println(name + " вошел в метод FirstClass.foo()");

//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {
//            System.out.println("Класс FirstClass прерван");
//        }

        System.out.println(name + " пытается вызвать метод SecondClass.last()");
        b.last();
    }

    synchronized void last() {
        System.out.println("В методе FirstClass.last()");
    }
}

class SecondClass {

    synchronized void bar(FirstClass a) {

        String name = Thread.currentThread().getName();
        System.out.println(name + " вошел в метод SecondClass.bar()");

//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {
//            System.out.println("Класс SecondClass прерван");
//        }

        System.out.println(name + " пытается вызвать метод FirstClass.last()");
        a.last();
    }

    synchronized void last() {
        System.out.println("В методе SecondClass.last()");
    }
}

public class DeadLockDemo {
    public static void main(String args[]) {
        FirstClass a = new FirstClass();
        SecondClass b = new SecondClass();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                a.foo(b);
                System.out.println("Возврат из foo");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                b.bar(a);
                System.out.println("Возврат из bar");
            }
        });
        t2.start();
        t1.start();
    }
}
