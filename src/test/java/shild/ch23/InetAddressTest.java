package shild.ch23;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InetAddress address=InetAddress.getLocalHost();
			System.out.println(address);
			address=InetAddress.getByName("www.herbschildt.com");
			System.out.println(address);
			InetAddress[] addresses=InetAddress.getAllByName("www.nba.com");
			address=InetAddress.getByAddress(new byte[]{8,8,8,8});
			System.out.println(address.getHostName());
			for(InetAddress adr : addresses)
				System.out.println(adr);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
