package com.aizain.jhome.design.pattern.algorithm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * ThreadUseAwaitAndNotify
 *
 * @author Zain
 * @date 2019-08-02
 */
public class ThreadUseAwaitAndNotify {

    private static final int MAX_POOL_SIZE = 64;
    private static final ExecutorService THREAD_POOL = ThreadUseAwaitAndNotify.createPool(64);
    private static final Object LOCK = new Object();
    private static Integer num = 1;

    public static void main(String[] args) {
        printAny(100, 4, 5);
    }

    private static void printAny(Integer max, Integer threadNum, Integer step) {
        if (threadNum > MAX_POOL_SIZE) {
            threadNum = MAX_POOL_SIZE;
        }
        int index = 0;
        while (index < threadNum) {
            int finalIndex = index;
            Integer finalThreadNum = threadNum;
            THREAD_POOL.execute(() -> lockPrintNumLoop(
                    "Thread-" + (finalIndex + 1),
                    () -> num,
                    () -> num += step,
                    () -> num < max,
                    (localNum) -> (localNum - 1) % finalThreadNum != finalIndex,
                    LOCK
            ));
            index++;
        }
        THREAD_POOL.shutdown();
    }

    private static void lockPrintNumLoop(
            String thread,
            Supplier<Integer> num,
            Supplier<Integer> process,
            Supplier<Boolean> inLoop,
            Function<Integer, Boolean> enableLock,
            Object lockObj) {
        while (inLoop.get()) {
            lockPrintNum(inLoop, num, enableLock, lockObj);
            if (inLoop.get()) {
                printNum(thread, num.get());
                process.get();
            }
        }
        synchronized (lockObj) {
            lockObj.notifyAll();
        }
    }

    private static void lockPrintNum(Supplier<Boolean> inLoop, Supplier<Integer> num, Function<Integer, Boolean> enableLock, Object lockObj) {
        synchronized (lockObj) {
            lockObj.notifyAll();
            while (enableLock.apply(num.get()) && inLoop.get()) {
                try {
                    lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void printNum(String thread, Integer number) {
        System.out.println(thread + " say: " + number);
    }

    private static ExecutorService createPool(int i) {

        return new ThreadPoolExecutor(
                i,
                i,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

}
