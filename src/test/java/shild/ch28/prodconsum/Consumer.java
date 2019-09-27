package shild.ch28.prodconsum;

public class Consumer implements Runnable{
	
	private Q q;

	public Consumer(Q q) {
		super();
		this.q = q;
		new Thread(this,"Consumer").start();
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<20;i++){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			q.get();
		}
	}

}
