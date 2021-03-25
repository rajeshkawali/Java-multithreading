package com.rajeshkawali.Executors;

public class ThreadClass {

	public static void main(String[] args) {
		// If we want to create 100 threads to complete 100 task then this is tedious task for JVM to create threads instead use ExecutorService
		for (int i = 0; i < 100; i++) {
			Thread thread = new Thread(new Task());
			thread.start();
		}
		System.out.println("Thread Name From Main :" + Thread.currentThread().getName());
	}
}

class Task implements Runnable {
	public void run() {
		System.out.println("Thread Name :" + Thread.currentThread().getName());
	}
}