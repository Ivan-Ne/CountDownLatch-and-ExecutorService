import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        // Creating executor service for 2 threads.
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Counter for 10 threads.
        CountDownLatch countDownLatch = new CountDownLatch(10);

        // Cycle for creating 10 threads.
        for (int i  = 0; i < 10; i++){
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Start " + index);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Finish " + index);
                    // Decreases the value of the counter for 1.
                    countDownLatch.countDown();
                }
            });
        }

        // Finish of executor service.
        executorService.shutdown();

        // Method "await" stops the main thread when the counter's value is 0.
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The last action of this code.
        System.out.println("All threads are terminated.");
    }
}
