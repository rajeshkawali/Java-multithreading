package com.rajeshkawali.ProducerConsumer_7;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//Producer Consumer without using BlockingQueue. create your own BlockingQueue
public class ProducerConsumer {
	public static void main(String[] args) {
		myBlockingQueue bq = new myBlockingQueue(10);

	}
}

class myBlockingQueue<E> {

	private int max;
	private Queue<E> queue = new LinkedList<E>();
	private ReentrantLock reLock = new ReentrantLock(true);
	private Condition notEmpty = reLock.newCondition();
	private Condition notFull = reLock.newCondition();

	public myBlockingQueue(int size) {
		queue = new LinkedList<E>();
		this.max = size;
	}

	public void put(E e) {
		reLock.lock();
		try {
			if (queue.size() == max) {
				try {
					notFull.await();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			queue.add(e);
			notEmpty.signalAll();
		} finally {
			reLock.unlock();
		}
	}

	public E add(E e) {
		reLock.lock();
		try {
			if (queue.size() == 0) {
				try {
					notEmpty.await();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			E item = queue.remove();
			notFull.signalAll();
			return item;
		} finally {
			reLock.unlock();
		}
	}
}
