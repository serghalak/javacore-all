package internet.test;
public	class Main{ 		 
		   public static void main(String...args){ 
		       C c = new C(); 		 
		   } 		 
		} 
		class A{ 
		   static{ System.out.println("static A1");  } 
		    A(){  System.out.println("A"); } 
		} 
		class B extends A{  static{  System.out.println("static B1");    } 
		    B(){   System.out.println("B");    } 
		} 
		class C extends B{  static{   System.out.println("static C1"); 	  } 
		   { 
		       System.out.println("non static C1"); 
		   } 
		    C(){ 
		        this("Hello"); 
		        System.out.println("C1"); 
		    } 
		    C(String str){ 
		        System.out.println("C2"); 
		    } 
		    { 
		        System.out.println("non static C2"); 
		   } 
		}
	
	

