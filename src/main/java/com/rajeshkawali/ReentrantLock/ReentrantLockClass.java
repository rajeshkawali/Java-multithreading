package com.rajeshkawali.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;
/*
The ReentrantLock class implements the Lock interface and provides synchronization to methods 
while accessing shared resources. The code which manipulates the shared resource is surrounded 
by calls to lock and unlock method. This gives a lock to the current working thread and blocks 
all other threads which are trying to take a lock on the shared resource. 

1. Create an object of ReentrantLock
2. Create a worker(Runnable Object) to execute and pass the lock to the object
3. Use the lock() method to acquire the lock on shared resource
4. After completing work, call unlock() method to release the lock 
*/

public class ReentrantLockClass {
	//ReentrantLock name because if its has lock while accessing the resource if it has recursive call then it will not have to wait for the lock because already it has lock(owner of the lock). to re enter in to the locked method is called ReentrantLock.
	static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {
		for (int i = 0; i <= 10; i++) {
			Thread thread = new Thread(new RestrictAccess(lock));
			thread.start();
        }
	}
}

class RestrictAccess implements Runnable {
	private ReentrantLock lock;
	
public RestrictAccess(ReentrantLock lock) {
	this.lock = lock;
}
	public void run() {
		lock.lock();
		System.out.println("Waiting....");
		try {
			Thread.sleep(1000); // Some resource execution here which takes 2 seconds. Other threads are waiting to unlock the resource
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		System.out.println("unlock....");
	}

}
/*
lock(): Call to the lock() method increments the hold count by 1 and gives the lock to the thread if the shared resource is initially free.
unlock(): Call to the unlock() method decrements the hold count by 1. When this count reaches zero, the resource is released.
tryLock(): If the resource is not held by any other thread, then call to tryLock() returns true and the hold count is incremented by one. If the resource is not free, then the method returns false, and the thread is not blocked, but exits.
tryLock(long timeout, TimeUnit unit): As per the method, the thread waits for a certain time period as defined by arguments of the method to acquire the lock on the resource before exiting.
lockInterruptibly(): This method acquires the lock if the resource is free while allowing for the thread to be interrupted by some other thread while acquiring the resource. It means that if the current thread is waiting for the lock but some other thread requests the lock, then the current thread will be interrupted and return immediately without acquiring the lock.
getHoldCount(): This method returns the count of the number of locks held on the resource.
isHeldByCurrentThread(): This method returns true if the lock on the resource is held by the current thread.
*/

