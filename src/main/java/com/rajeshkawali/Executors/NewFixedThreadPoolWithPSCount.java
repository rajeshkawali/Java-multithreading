package com.rajeshkawali.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPoolWithPSCount {

	public static void main(String[] args) {
		// Create only available processors in the system
		int processorCount = Runtime.getRuntime().availableProcessors();// give count of CPU cores
		ExecutorService executor = Executors.newFixedThreadPool(processorCount); // Thread Pool
		for (int i = 0; i < 100; i++) {
			executor.execute(new CPUIntensiveTask()); // Execute the task
		}
		System.out.println("Thread Name From Main :" + Thread.currentThread().getName());
	}

}
class CPUIntensiveTask implements Runnable {
	public void run() {
		System.out.println("Thread Name :" + Thread.currentThread().getName());
	}
}