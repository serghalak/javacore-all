package horsman.v11.t1.ch06;

import horsman.v11.t1.ch05.Employee;
import horsman.v11.t1.ch05.Manager;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Employee empl1=new Employee("Serg",10000,2019,7,8);
		Employee cloneEmpl1= (Employee) empl1.clone();
		System.out.println("Serg: "+empl1.getName().hashCode());
		System.out.println("Serg: "+cloneEmpl1.getName().hashCode());
		String strName="Serg";
		String strName1="Serg";
		String strNewName=new String("Serg");
		String strNewName1=new String("Serg");
		System.out.println(strName==strName1);
		System.out.println((strName==strNewName)+" "+(strNewName==strNewName1));
		System.out.println(strNewName.hashCode());
		System.out.println(strNewName1.hashCode());
		empl1.setName(strNewName);
		System.out.println("Serg: "+empl1.getName().hashCode());
		System.out.println("Serg: "+cloneEmpl1.getName().hashCode());
		System.out.println("empl1: " + empl1);
		System.out.println("cloneEmpl1: " + cloneEmpl1);
		
		Employee m=new Manager("M",12000,1999,11,1);
		
		
	}

}
