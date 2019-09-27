package shild.ch28.executor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import shild.ch28.countdownlatch.MyThread;

public class SimpExec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountDownLatch cdl=new CountDownLatch(5);
		CountDownLatch cdl2=new CountDownLatch(5);
		CountDownLatch cdl3=new CountDownLatch(5);
		CountDownLatch cdl4=new CountDownLatch(5);
		ExecutorService es=Executors.newFixedThreadPool(2);
		System.out.println("Run threads ...");
		es.execute(new MyThread(cdl,"A"));
		es.execute(new MyThread(cdl2,"B"));
		es.execute(new MyThread(cdl3,"C"));
		es.execute(new MyThread(cdl4,"D"));
		
		try {
			cdl.await();
			cdl2.await();
			cdl3.await();
			cdl4.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		es.shutdown();
		System.out.println("Finishing threads");
		
	}

}
