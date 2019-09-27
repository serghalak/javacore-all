package shild.ch28.phaser;

import java.util.concurrent.Phaser;

public class PhaserDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Phaser phsr=new Phaser(1);
		int curPhase;
		
		new Thread(new MyThread(phsr,"A")).start();
		new Thread(new MyThread(phsr,"B")).start();
		new Thread(new MyThread(phsr,"C")).start();
		
		curPhase=phsr.getPhase();
		phsr.arriveAndAwaitAdvance();
		System.out.println("Phasa " + curPhase+" ended");
		
		curPhase=phsr.getPhase();
		phsr.arriveAndAwaitAdvance();
		System.out.println("Phasa " + curPhase+" ended");
		
		curPhase=phsr.getPhase();
		phsr.arriveAndAwaitAdvance();
		System.out.println("Phasa " + curPhase+" ended");
		
		phsr.arriveAndDeregister();
		if(phsr.isTerminated())
			System.out.println("Synchonized phases was finished");
		
		
	}

}
