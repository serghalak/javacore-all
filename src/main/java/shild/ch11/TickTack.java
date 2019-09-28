package shild.ch11;

public class TickTack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clock clock=new Clock();
		
		Thread tick=new Thread(new Tic(clock));
		Thread tack=new Thread(new Tack(clock));
		tick.start();
		tack.start();
		try {
			tick.join();
			tack.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Main thread was finished");

	}

}

class Clock{
	boolean krit=false;
	
	synchronized void tick(){
		while(krit){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.print("Tick ---");
		krit=true;
		notify();
	}
	
	synchronized void tack(){
		while(!krit){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(" Tack");
		krit=false;
		notify();
	}
	
}

class Tic implements Runnable{
	Clock clock;
	
	public Tic(Clock clock) {
		super();
		this.clock = clock;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++){
			clock.tick();
		}
	}
}

class Tack implements Runnable{
	Clock clock;
	
	
	public Tack(Clock clock) {
		super();
		this.clock = clock;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(1000);
				clock.tack();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

