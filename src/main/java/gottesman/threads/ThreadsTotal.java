package gottesman.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadsTotal {

	AtomicInteger total = new AtomicInteger();

	public void add() {
		total.incrementAndGet();
	}

	public void totalAll() throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(6);
	
		for (int i = 0; i < 10000; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					add();
				}
			};

			service.execute(runnable);
		}

		service.shutdown();
		service.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println(total);
	}

	public static void main(String[] args) throws InterruptedException {
		new ThreadsTotal().totalAll();
	}

}