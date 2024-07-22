package Threading;

public class checkThenActRace implements Runnable {

	int balance=100;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		checkThenActRace checkThenActRace= new checkThenActRace();
		Thread yogesh= new Thread(checkThenActRace);
		Thread durgesh= new Thread(checkThenActRace);
		
		yogesh.start();
		durgesh.start();
		
		

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		System.out.println("Withdrwaing in process"+ Thread.currentThread().getName());
		if(balance>=100) {
			System.out.println("withdrawing money:"+ Thread.currentThread().getName());
			balance-=50;
			
		}else {
			System.out.println("withdrawl not possible :"+ Thread.currentThread());
		}
		System.out.println("Final balance="+ balance);
		
	}

}
