package shild.ch28.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class MyThread implements Runnable{
	
	private CountDownLatch latch;
	
	private String name;

	public MyThread(CountDownLatch latch) {
		super();
		this.latch = latch;
		this.name="NONAME";
	}

	public MyThread(CountDownLatch latch, String name) {
		super();
		this.latch = latch;
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for(int i=0;i<5;i++){
			System.out.println(name +": " +i);
			latch.countDown();
		}
	}

}
