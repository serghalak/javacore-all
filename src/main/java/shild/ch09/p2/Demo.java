package shild.ch09.p2;


import shild.ch09.p1.Derived;
import shild.ch09.p1.Protection;

import shild.ch09.p1.SamePackage;

public class Demo {
	  public static void main(String args[]) {
	    Protection ob1=new Protection();
	    Derived ob2 = new Derived();
	    SamePackage ob3 = new SamePackage();
		  Protection2 ob1_1=new Protection2();
		  OtherPackage ob2_1=new OtherPackage();
		 
	  }
	}



	

