package Threading;

public class readModifyWriteRace implements Runnable {

	
	int shared=0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for(int i=0;i<10000;i++) {
			shared++;
		}
		
	}
	public int getCount() {
		return shared;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readModifyWriteRace readModifyWriteRace= new readModifyWriteRace();
		Thread t1= new Thread(readModifyWriteRace);
		Thread t2= new Thread(readModifyWriteRace);
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		}catch (InterruptedException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	    System.out.println("final value ="+readModifyWriteRace.getCount());
	}
}




