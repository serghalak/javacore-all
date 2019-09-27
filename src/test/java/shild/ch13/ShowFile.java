package shild.ch13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ShowFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		InputStream is=new FileInputStream("d:/horsman.txt");
		int i;
		while((i=is.read()) != -1){
			System.out.print((char)i);
		}
	}

}
