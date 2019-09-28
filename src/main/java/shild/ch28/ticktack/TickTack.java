package shild.ch28.ticktack;

import java.util.concurrent.Semaphore;

public class TickTack {
	static Semaphore semTick=new Semaphore(1);
	static Semaphore semTack=new Semaphore(0);
	

	
	public void goTick(){	
		
		try {
			semTick.acquire();
			System.out.print("TICK --- ");
			Thread.sleep(1000);
			semTack.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void goTack(){
		try {
			semTack.acquire();
			System.out.println(" TACK");
			Thread.sleep(1000);
			semTick.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
