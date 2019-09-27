package shild.ch28.prodconsum;

public class Producer implements Runnable {

	private Q q;
	
	
	
	public Producer(Q q) {
		super();
		this.q = q;
		new Thread(this,"Producer").start();
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<20;i++){
			q.put(i);
		}
	}

}
