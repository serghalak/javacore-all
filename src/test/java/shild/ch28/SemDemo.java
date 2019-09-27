package shild.ch28;

import java.util.concurrent.Semaphore;

public class SemDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Semaphore sem = new Semaphore(1);
		
		new Thread(new IncThread(sem,"A")).start();;
		new Thread(new DecThread(sem,"B")).start();
	}

}
