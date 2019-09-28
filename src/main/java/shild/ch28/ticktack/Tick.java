package shild.ch28.ticktack;

public class Tick implements Runnable{
	
	private TickTack tt;

	public Tick(TickTack tt) {
		super();
		this.tt = tt;
		new Thread(this).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++){
			tt.goTick();
		}
	}
	
	

}
