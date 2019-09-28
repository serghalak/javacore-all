package shild.ch28.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Exchanger<String>exchr=new Exchanger<String>();
		new Thread(new UseString(exchr)).start();
		new Thread(new MakeString(exchr)).start();
	}

}
