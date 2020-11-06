package horsman.v11.t1.ch03;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class IOFiles {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		byte k= (byte) 254;
		int i = Byte.toUnsignedInt(k);
		System.out.println(i);
		System.out.println("---> %  " + Math.floorMod(-35,8));
		System.out.println("---> " + (-35%8));
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Hello. What is your name: ");
//		String name=scanner.nextLine();
//		System.out.println("Hello: "+name);
		//ReadWriteFile();
		
		String str1="boy";
		String str2 ="boy";
		String str3=new String("boy");
		System.out.println("str1==str2 "+(str1==str2));
		System.out.println("str1==str3 "+(str1==str3));
		System.out.println(""+Integer.toHexString(127));

	}
	
	static void ReadWriteFile() throws FileNotFoundException{
//		PrintWriter writer=
//				new PrintWriter(
//						"d://horsman.txt"
//						//,StandardCharsets.UTF_8);
//						);
//		writer.write("Hello world!!!");
//		writer.flush();
		Scanner scanner=new Scanner(new File("d://horsman.txt"));
		//Scanner scanner=new Scanner(Path.of("d://horsman.txt"));
		String fileStr=scanner.nextLine();
		System.out.println(System.getProperty("user.dir"));
		System.out.println(fileStr);
//		Arrays.copy
//		{
//			Scanner scanner=new Scanner(new File("d.txt"));
//			
//		}
	}

}
