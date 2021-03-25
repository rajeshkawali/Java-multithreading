package com.rajeshkawali.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewSingleThreadExecutorClass {

	public static void main(String[] args) {
		// Only one thread will be created to execute the task
		ExecutorService executor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 100; i++) {
			executor.execute(new MyTask()); // Execute the task
		}
		System.out.println("Thread Name From Main :" + Thread.currentThread().getName());
	}

}
class MyTask implements Runnable {
	public void run() {
		System.out.println("Thread Name :" + Thread.currentThread().getName());
	}
}