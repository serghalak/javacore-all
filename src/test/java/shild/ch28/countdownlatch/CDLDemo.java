package shild.ch28.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CDLDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountDownLatch cdl=new CountDownLatch(5);
		System.out.println("Run a thread");
		new Thread(new MyThread(cdl)).start();
		try{
			cdl.await();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		System.out.println("The thread is finishing ...");
	}

}
