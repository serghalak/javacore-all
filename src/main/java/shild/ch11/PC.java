package shild.ch11;

public class PC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Q q =new Q();
		Thread prod=new Thread(new Producer(q));		
		Thread con=new Thread(new Consumer(q));
		prod.start();
		con.start();
		try {
			prod.join();
			con.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Main thread is out ...");		
	}
}

class Q{
	int n;	
	boolean krit=false;
	
	int getN(){return n;}
	
	synchronized int get(){		
		while(!krit){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("  ----  consumed ..."+n);
		krit=false;
		notify();
		return n;

		
	}
	synchronized void set(int n){		
		while(krit){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.n=n;
		krit=true;
		System.out.print("producing ..."+n);
		notify();
	
	}
}
class Producer implements Runnable{	
	private Q q;

	public Producer(Q q) {
		super();
		this.q = q;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for(int n=0;n<100;n++){
			
			q.set(n);
		}
	}
	
}

class Consumer implements Runnable{
	private Q q;

	public Consumer(Q q) {
		super();
		this.q = q;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++){
			q.get();
			int res=q.get();
			
		}
	}	
} 