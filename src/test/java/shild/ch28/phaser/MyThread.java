package shild.ch28.phaser;

import java.util.concurrent.Phaser;

public class MyThread implements Runnable{
	private Phaser phsr;
	private String name;
	public MyThread(Phaser phsr, String name) {
		super();
		this.phsr = phsr;
		this.name = name;
		phsr.register();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread " + name + " starts first phase");
		phsr.arriveAndAwaitAdvance();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Thread "+name+" start second phase");
		phsr.arriveAndAwaitAdvance();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Thread "+ name +" starts third phase");
		//phsr.arriveAndAwaitAdvance();
		phsr.arriveAndDeregister();
	}
	
	
	

}
