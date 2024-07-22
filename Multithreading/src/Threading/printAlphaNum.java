package Threading;

public class printAlphaNum {

	private static boolean flag=  true;
	private static final Object lock= new Object();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Runnable letterTask= new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				for(char ch='A'; ch<='Z'; ch++) {
					synchronized (lock) {
					
						if(!flag) {
							try {
								lock.wait();
							}catch(InterruptedException e){
								throw new RuntimeException(e);
							}
						}
						System.out.print(" "+ ch);
						flag= false;
						lock.notify();
					}
				}
				
			}
		};
		
Runnable numTask= new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				for(int num=1; num<=26; num++) {
					synchronized (lock) {
					
						if(flag) {
							try {
								lock.wait();
							}catch(InterruptedException e){
								throw new RuntimeException(e);
							}
						}
						System.out.print(" "+ num);
						flag= true;
						lock.notify();
					}
				}
				
			}
		};
		
		Thread letterThread= new Thread(letterTask);
		Thread numThread= new Thread(numTask);
		
		letterThread.start();
		numThread.start();
		
		try {
			letterThread.join();
			numThread.join();
		}catch (InterruptedException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
		
	}

}
