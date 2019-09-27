package shild.ch28.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyThread implements Runnable{
	private CyclicBarrier cyclicBarrier;
	private String name;

	public MyThread(CyclicBarrier cyclicBarrier, String name) {
		super();
		this.cyclicBarrier = cyclicBarrier;
		this.name=name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(name);
		try {
			Thread.sleep(1000);
			System.out.println("end of thread "+ name);
			cyclicBarrier.await();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
