package internet.test;

class A1 { } 
class B1 extends A1 { } 
public class Main1 extends B1 { 
		
		public static void main(String[] args) { 
		     A1 obj1 = new A1(); 
		     Main1 obj2 = new Main1(); 
		      // програмный код 
		      Main1 obj3 = (Main1)obj1; 
	    } 
} 
	
