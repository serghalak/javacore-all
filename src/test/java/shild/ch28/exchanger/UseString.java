package shild.ch28.exchanger;

import java.util.concurrent.Exchanger;

public class UseString implements Runnable{
	
	Exchanger<String>ex;
	String str;
	public UseString(Exchanger<String> ex) {
		super();
		this.ex = ex;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<3;i++){
			try{
				str=ex.exchange(new String());
				System.out.println("Received: " + str);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	

}
