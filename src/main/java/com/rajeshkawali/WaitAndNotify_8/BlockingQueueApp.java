package com.rajeshkawali.WaitAndNotify_8;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class BlockingQueue<T> {

    private Queue<T> queue = new LinkedList<T>();
    private int capacity;
    private Lock lock = new ReentrantLock();
    //condition variables
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(T element) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                System.out.println("queue is full cannot put");
                notFull.await(); //releases lock
            }

            queue.add(element);
            System.out.println("Added to the queue " + element);
            notEmpty.signal(); //calls waiting thread on the same object
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                System.out.println("queue is empty, cannot take");
                notEmpty.await(); //releases lock
            }

            T item = queue.remove();
            System.out.println("Removed to the queue " + item);
            notFull.signal(); //calls waiting thread on same object
            return item;
        } finally {
            lock.unlock();
        }
    }
}

@SuppressWarnings("InfiniteLoopStatement")
public class BlockingQueueApp {

    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue<Integer> blockingQueue = new BlockingQueue<Integer>(10);
        final Random random = new Random();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        blockingQueue.put(random.nextInt(10));
                    }
                } catch (InterruptedException ignored) {}
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);//wait for putting to the queue first
                } catch (InterruptedException ex) {
                    System.out.println("Exception " + ex.getMessage());
                }
                try {
                    while (true) {
                        blockingQueue.take();
                    }
                } catch (InterruptedException ignored) {}
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
