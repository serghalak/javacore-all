package shild.ch13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BRRead {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		char c;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("enter some characters 'q' for quit");
		do{			
			c=(char)0x0001F309;
			System.out.println(c);
			c=(char) br.read();
		}while(c != 'q');
	}

}
