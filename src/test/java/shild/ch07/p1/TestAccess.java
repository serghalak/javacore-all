package shild.ch07.p1;

import shild.ch07.Test;
import shild.ch07.TestExt;
import shild.ch07.TestExtExt;

public class TestAccess extends Test{

	public void test(){
		Test ob=new Test();
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test ob=new Test();
		
		//ob.textProtected();
		//ob.а=10;
		//ob.Ь=20;
		Test obj=new Test();
//		Object objClone=obj.clone();
		TestExt testExt=new TestExt();
		//testExt.textPackage();
		//obj.clone();
		
		//ob.packageString="pack str";
		//ob.protectedString="prot str";
		//ob.publicString="pub str";
//		System.out.println(""+ob.packageString);
//		System.out.println(""+ob.protectedString);
		System.out.println(""+ob.publicString);
		
		System.out.println(""+ob.getPublicString());
	}

}
