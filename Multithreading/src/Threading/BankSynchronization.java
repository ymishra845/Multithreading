package Threading;

public class BankSynchronization {

	private double balance;
	public BankSynchronization(double intitialBalance) {
		this.balance= intitialBalance;
	}
	
	public void deposit(double amount) {
		synchronized (this) {
			
		
		double newBalance= balance+amount;
		try {
			Thread.sleep(1000);
		}catch (InterruptedException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		balance= newBalance;
		}
		System.out.println("remaining balance is:"+ balance);
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void withdraw(double amount) {
		synchronized (this) {
			
		
		if(balance>=amount) {
			double newBalance =balance-amount;
		
		
		try {
			Thread.sleep(1000);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 balance= newBalance;
		}
		}
		System.out.println("remaining balance after withdrawal="+ balance);
	}

	public static void main(String[] args) {
		
	BankSynchronization bankSynchronization= new BankSynchronization(100);
	Thread deposit1= new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			bankSynchronization.deposit(40);
			
		}
	});
	
	Thread deposit2= new Thread(()-> bankSynchronization.deposit(40),"thread is running"+Thread.currentThread().getName()); // lamda way 
	Thread withdraw1 = new Thread(()-> bankSynchronization.withdraw(30),"thread is running"+Thread.currentThread().getName()); 
	Thread withdraw2 = new Thread(()-> bankSynchronization.withdraw(30),"thread is running"+Thread.currentThread().getName()); 
	
	deposit1.start();
	withdraw1.start();
	deposit2.start();
	
	withdraw2.start();
	
	try {
		deposit1.join();
		deposit2.join();
		withdraw1.join();
		withdraw2.join();
		
	}catch (InterruptedException e) {
		// TODO: handle exception
		throw new RuntimeException(e);
	}

	System.out.println("balance="+ bankSynchronization.getBalance());
	}

}
