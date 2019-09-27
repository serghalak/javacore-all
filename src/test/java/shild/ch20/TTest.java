package shild.ch20;

import java.util.Currency;
import java.util.Timer;
import java.util.TimerTask;

class MyTimerTask extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Task is running on timer ....");
	}
	
}

public class TTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		MyTimerTask task=new MyTimerTask();
//		Timer timer = new Timer();
//		timer.schedule(task, 1000,500);
//		try {
//			Thread.currentThread().sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		timer.cancel();
		System.out.println(Currency.getAvailableCurrencies());
		System.out.println(Currency.getAvailableCurrencies().size());
	}

}
