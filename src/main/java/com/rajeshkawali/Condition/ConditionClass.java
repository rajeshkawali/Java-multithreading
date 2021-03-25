package com.rajeshkawali.Condition;

import java.util.LinkedList;
import java.util.Queue;
// Condition interface in Java. A Condition object, also known as condition variable, provides a thread with the ability to suspend its execution, until the condition is true. A Condition object is necessarily bound to a Lock and can be obtained using the newCondition() method.
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionClass {

	public static void main(String[] args) throws InterruptedException {
		ProducerConsumer cp = new ProducerConsumer(10);
		cp.consumerMethod();
	}
}

class ProducerConsumer {
	private Lock lock = new ReentrantLock();
	private Condition added = lock.newCondition();
	private Condition removed = lock.newCondition();
	private Queue queue = new LinkedList();
	private volatile int maxCount = 100;

	public ProducerConsumer(int maxCount) {
		this.maxCount = maxCount;
	}

	public void producerMethod(Object obj) throws InterruptedException {
		lock.lock();
		try {
			while (queue.size() == maxCount) {
				removed.await();
			}
			queue.add(obj);
			added.signal();
		} finally {
			lock.unlock();
		}
	}

	public Object consumerMethod() throws InterruptedException {

		lock.lock();
		try {
			while (queue.size() == 0) {
				added.await();
			}
			Object obj = queue.remove();
			removed.signal();

			return obj;
		} finally {
			lock.unlock();
		}
	}
}
