package com.rajeshkawali.Executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewScheduledThreadPoolClass {

	public static void main(String[] args) {
		//It uses DelayQueue . It has 3 methods 1.schedule 2.scheduleAtFixedRate 3.scheduleAtFixedDelay
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
		executor.schedule(new CPUTask(), 10, TimeUnit.SECONDS);//run after 10 seconds
		executor.scheduleAtFixedRate(new CPUTask(), 15, 10, TimeUnit.SECONDS);//run repeatedly every 10 seconds
		executor.scheduleWithFixedDelay(new CPUTask(), 15, 10, TimeUnit.SECONDS);//run repeatedly 10 seconds after previous task complete
		System.out.println("Thread Name From Main :" + Thread.currentThread().getName());
	}

}

class CPUTask implements Runnable {
	public void run() {
		System.out.println("Thread Name :" + Thread.currentThread().getName());
	}
}