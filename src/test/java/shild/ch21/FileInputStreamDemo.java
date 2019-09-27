package shild.ch21;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileInputStreamDemo {
	private static final String PATH="src/test/java/shild/ch21/";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size;
		
		try(InputStream is=new FileInputStream(PATH+"FileInputStreamDemo.java");) {
			//System.out.println(new File("FileInputStreamDemo.java").getAbsoluteFile());
			System.out.println("Total available bite(s): " + (size=is.available()));
			int n=size/40;
			System.out.println("First " + n + " bytes were read from queue using method read");
//			byte[] buff=new byte[n];
//			int r=is.read(buff);
//			System.out.print(new String(buff));
//			int code=is.read();
//			System.out.println(code);
			for(int i=0;i<n;i++){
				System.out.print((char)is.read());
			}
			System.out.println("\nStill available: " + is.available());
			System.out.println("Reading next " + n
			+ "byte using method read(b[] )");
			byte[] b=new byte[n];
			if(is.read(b) != n){
				System.out.println("impossible to read "+n +" bytes");
			}
			System.out.println(new String(b,0,n,"UTF-8"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
