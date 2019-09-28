package shild.ch07;

public class AccessTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		//Test ob=new Test();
		TestExtExt ob=new TestExtExt();
		ob.textProtected();
		ob.а=10;
		ob.Ь=20;
		Test obj=new Test();
//		Object objClone=obj.clone();
		TestExt testExt=new TestExt();
		testExt.textPackage();
		//obj.clone();
		
		//ob.packageString="pack str";
		//ob.protectedString="prot str";
		//ob.publicString="pub str";
		System.out.println(""+ob.packageString);
		System.out.println(""+ob.protectedString);
		System.out.println(""+ob.publicString);
		
		System.out.println(""+ob.getPublicString());
		//ob.text();
		//ob.text1();
	}

}



