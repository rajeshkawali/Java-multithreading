package com.rajeshkawali.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPoolClass {

	public static void main(String[] args) {
		// Just create 10 threads to complete 100 task using ExecutorService
		ExecutorService executor = Executors.newFixedThreadPool(10); // Thread Pool
		for (int i = 0; i < 100; i++) {
			executor.execute(new Work()); // Submit the task
		}
		System.out.println("Thread Name From Main :" + Thread.currentThread().getName());
	}

}
class Work implements Runnable {
	public void run() {
		System.out.println("Thread Name :" + Thread.currentThread().getName());
	}
}