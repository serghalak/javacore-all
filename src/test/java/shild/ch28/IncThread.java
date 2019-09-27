package shild.ch28;

import java.util.concurrent.Semaphore;

public class IncThread implements Runnable{
	
	private String name;
	private Semaphore sem;
	

	public IncThread(Semaphore sem , String name) {
		super();
		this.name = name;
		this.sem = sem;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Run thread: " + name);
		try{
			System.out.println("Thread " + name + " is waiting for acquire");
			sem.acquire();
			System.out.println("Thread " +  name + " is getting acquire");
			for(int i=0 ; i<5 ; i++){
				Shared.count++;
				System.out.println(name + ": " + Shared.count);
				Thread.sleep(10);
			}
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		System.out.println("Thread " + name + " release acquire");
		sem.release();
		
	}
	
	

}
