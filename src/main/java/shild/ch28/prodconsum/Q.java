package shild.ch28.prodconsum;

import java.util.concurrent.Semaphore;

public class Q {
	int n;
	static Semaphore semCon=new Semaphore(0);
	static Semaphore semProd=new Semaphore(1);
	
	
	void get(){
		try{
			semCon.acquire();
			System.out.println("Got: " + n);
			semProd.release();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
	}
	
	
	void put(int n){
		try{
			semProd.acquire();
			this.n=n;
			System.out.println("Sent: " + this.n);
			semCon.release();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
	}
	

}
