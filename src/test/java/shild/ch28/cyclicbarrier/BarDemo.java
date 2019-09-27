package shild.ch28.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class BarDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CyclicBarrier cb= new CyclicBarrier(3, new BarAction());
		System.out.println("Run threads");
		
		new Thread(new MyThread(cb,"A")).start();
		new Thread(new MyThread(cb,"B")).start();
		new Thread(new MyThread(cb,"C")).start();

	}

}
