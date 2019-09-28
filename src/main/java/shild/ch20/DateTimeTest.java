package shild.ch20;

import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GregorianCalendar gCalendar=new GregorianCalendar();
		TimeZone tz=TimeZone.getDefault();
		
		System.out.println(tz.getID());
	}

}
