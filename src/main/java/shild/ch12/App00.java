package shild.ch12;

import java.io.UnsupportedEncodingException;

public class App00 {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
		System.out.println("A".getBytes().length);
		System.out.println("AA".getBytes().length);
		
		System.out.println("A".getBytes("UTF-16").length);
		System.out.println("AA".getBytes("UTF-16").length);
		byte[] str="AA".getBytes("UTF-16");
		String s=new String(str);
		System.out.println("s="+s);
		//Arrays.to
		System.out.println(str[0]+":"+str[1]);
	}

}
