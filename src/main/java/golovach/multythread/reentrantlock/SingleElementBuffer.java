package golovach.multythread.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SingleElementBuffer  {
    private final Lock lock = new ReentrantLock(true);
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    private Integer elem = null;

    public void put(int newElem) throws InterruptedException {
        lock.lock();
        try {
            while (this.elem != null) {
                notFull.await(); // await not wait
            }
            this.elem = newElem;
            notEmpty.signal(); //signal not signalAll
        } finally {
            lock.unlock();
        }
    }

    public boolean tryPut(int newElem) throws InterruptedException {
        if (lock.tryLock()) {
            try {
                if (this.elem != null) {
                    notFull.await();
                }
                this.elem = newElem;
                notEmpty.signal();
            } finally {
                lock.unlock();
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean tryPut(int newElem, long timeout, TimeUnit units) throws InterruptedException {
//        if (lock.tryLock()) {
//            try {
//                if (this.elem != null) {
//                    notFull.await();
//                }
//                this.elem = newElem;
//                notEmpty.signal();
//            } finally {
//                lock.unlock();
//                return true;
//            }
//        } else {
//            return false;
//        }

        return false;
    }

    public int get() throws InterruptedException {
        lock.lock();
        try {
            while (this.elem == null) {
                notEmpty.await();
            }
            int result = this.elem;
            this.elem = null;
            notFull.signal();
            return result;
        } finally {
            lock.unlock();
        }
    }
}

class T {
    static void main() {
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        f();
                    }
                }
            }).start();
        }
    }

    public static synchronized void f() {
        System.out.println(Thread.currentThread().getName());
    }
}