package shild.ch28.exchanger;

import java.util.concurrent.Exchanger;

public class MakeString implements Runnable{
	
	Exchanger<String> ex;
	String str;
	
	
	
	public MakeString(Exchanger<String> ex) {
		super();
		this.ex = ex;
		str=new String();
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		char ch='A';
		
		for(int i=0;i<3;i++){
			for(int j=0;j<5;j++){
				str +=(char)ch++;
			}
			
			try{
				str=ex.exchange(str);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}
