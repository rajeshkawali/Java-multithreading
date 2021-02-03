package com.rajeshkawali.InterruptingThreads_14;

import java.util.concurrent.*;


public class App {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting.");

        ExecutorService executor = Executors.newCachedThreadPool();

        Future<?> fu = executor.submit(new Callable<Void>() {

            
            public Void call() throws Exception {

                for (int i = 0; i < 1E8; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.printf("Interrupted at %d !!!", i);
                        break;
                    }
                }

                return null;
            }
        });

        executor.shutdown();
        Thread.sleep(500);

        /*
        in this example, there are different ways you can interrupt a thread
        execution.
         */

//        fu.cancel(true);

        executor.shutdownNow();

        executor.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Finished.");
    }

}
