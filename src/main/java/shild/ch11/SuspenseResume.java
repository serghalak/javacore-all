package shild.ch11;

public class SuspenseResume {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NewThread1 t= new NewThread1("New");
		try {
			Thread.sleep(2000);
			t.mySuspend();
			Thread.sleep(3000);
			t.myResume();
			t.t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ok!!!");
	}

}


class NewThread1 implements Runnable{
	boolean suspendFlag=false;
	Thread t;
	String name;
	

	public NewThread1(String name) {
		super();
		this.name=name;
		t=new Thread(this, name);
		t.start();
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0 ; i<15 ; i++){
			System.out.println("i="+i);
			try {
				Thread.sleep(500);
				synchronized(this){
				while(suspendFlag){
					wait();
				}}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public synchronized void mySuspend(){
		suspendFlag=true;
	}
	
	public synchronized void myResume(){
		suspendFlag=false;
		notify();
	}
	
}