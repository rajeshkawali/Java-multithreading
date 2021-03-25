package com.rajeshkawali.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewCachedThreadPoolClass {

	public static void main(String[] args) {
		// We don't know how many threads going to be created for the task
		//If all threads are busy then create a new thread for the task and place it in the pool
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			executor.execute(new WorkHard()); // execute the task
		}
		System.out.println("Thread Name From Main :" + Thread.currentThread().getName());
	}

}
class WorkHard implements Runnable {
	public void run() {
		System.out.println("Thread Name :" + Thread.currentThread().getName());
	}
}