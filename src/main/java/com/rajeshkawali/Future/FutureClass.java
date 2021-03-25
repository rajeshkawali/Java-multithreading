package com.rajeshkawali.Future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureClass {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(10);// 10 thread created
		Future<Integer> future = executor.submit(new Task());//It continue to execution of main thread
		System.out.println("Thread Name From Main :" + Thread.currentThread().getName());
		try {
			Integer result = future.get(); // It will block the execution of main thread until future arrive here
			//Integer resultWithTime = future.get(1, TimeUnit.SECONDS); // provide the time if not getting within mentioned time the reject
			System.out.println("Result from the Task is :" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Thread Name From Main :" + Thread.currentThread().getName());
	}
}

class Task implements Callable<Integer> {
	public Integer call() throws Exception {
		Thread.sleep(2000);
		return new Random().nextInt();
	}
}
