package Threading;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeIncDec {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  AtomicInteger counter= new AtomicInteger(0);
		Runnable incrementTask = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				for (int i = 0; i < 100000; i++) {
					counter.incrementAndGet();
				}

			}
		};

		Runnable decrementTask = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				for (int i = 0; i < 100000; i++) {
					counter.decrementAndGet();
				}

			}
		};
		
		Thread incrementThread= new Thread(incrementTask);
		Thread decrementThread= new Thread(decrementTask);
		
		incrementThread.start();
		decrementThread.start();
		
		try {
			incrementThread.join();
			decrementThread.join();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		System.out.println("Final counter value is=" +counter);
		
	}

}
