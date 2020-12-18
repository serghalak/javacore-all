package horsman.v11.t1.ch05;

import java.util.Arrays;
import java.util.EnumSet;

public class EqualsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//empMang();
		testwrappers();
		if(true)
			return;

		Manager boss=new Manager("Carl Cracker",80000,1987,12,15);
		boss.setBonus(5000);
		Employee harry=new Employee("Harry Hacker",50000,1989,10,1);
		Employee tommy=new Employee("Tommy Tester",40000,1990,3,15);

		Employee[] stuff=new Employee[]{boss,harry,tommy};

		Manager[] arrManager = new Manager[]{boss};
		Employee[] arrEmpl = new Employee[]{harry};
		arrEmpl=arrManager;
		arrEmpl[0]=tommy;
		arrManager[0].setBonus(1000);
		System.out.println(arrManager[0]);


		for (Employee empl : stuff){
			System.out.println("name="+ empl.getName()+", salary=" + empl.getSalary());
		}

		if(true)
			return;
//		  Employee alice1 = new Employee("Alice Adams", 75000, 1987, 12, 15);
//	      Employee alice2 = alice1;
//	      Employee alice3 = new Employee("Alice Adams", 75000, 1987, 12, 15);
//	      Employee bob = new Employee("Bob Brandson", 50000, 1989, 10, 1);
//
//	      System.out.println("alice1 == alice2: " + (alice1 == alice2));
//
//	      System.out.println("alice1 == alice3: " + (alice1 == alice3));
//
//	      System.out.println("alice1.equals(alice3): " + alice1.equals(alice3));
//
//	      System.out.println("alice1.equals(bob): " + alice1.equals(bob));
//
//	      System.out.println("bob.toString(): " + bob);
//
//	      Manager carl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
//	      Manager boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
//	      boss.setBonus(5000);
//	      System.out.println("boss.toString(): " + boss);
//	      System.out.println("carl.equals(boss): " + carl.equals(boss));
//	      System.out.println("alice1.hashCode(): " + alice1.hashCode());
//	      System.out.println("alice3.hashCode(): " + alice3.hashCode());
//	      System.out.println("bob.hashCode(): " + bob.hashCode());
//	      System.out.println("carl.hashCode(): " + carl.hashCode());
		
//		Size size=Size.LARGE;
//		System.out.println(""+size.toString());
//		System.out.println(""+size.name());
//		System.out.println(""+size.ordinal());
//		System.out.println(size.valueOf("LARGE"));
//
//		System.out.println(""+Enum.valueOf(Size.class, "SMALL"));
//		System.out.println(""+Arrays.toString(size.values()));
	}

	private static void empMang(){
	  Employee alice1 = new Employee("Alice Adams", 75000, 1987, 12, 15);
	  Employee alice2 = alice1;
	  Employee alice3 = new Employee("Alice Adams", 75000, 1987, 12, 15);
	  Employee bob = new Employee("Bob Brandson", 50000, 1989, 10, 1);

	  System.out.println("alice1 == alice2: " + (alice1 == alice2));

	  System.out.println("alice1 == alice3: " + (alice1 == alice3));
		System.out.println("alice1 hashcode: " + alice1.hashCode());
		System.out.println("alice3 hashcode: " + alice3.hashCode());
	  System.out.println("alice1.equals(alice3): " + alice1.equals(alice3));

	  System.out.println("alice1.equals(bob): " + alice1.equals(bob));

	  System.out.println("bob.toString(): " + bob);

	  Manager carl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
	  Manager boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
	  boss.setBonus(5000);
	  System.out.println("boss.toString(): " + boss);
	  System.out.println("carl.equals(boss): " + carl.equals(boss));
	  System.out.println("alice1.hashCode(): " + alice1.hashCode());
	  System.out.println("alice3.hashCode(): " + alice3.hashCode());
	  System.out.println("bob.hashCode(): " + bob.hashCode());
	  System.out.println("carl.hashCode(): " + carl.hashCode());
	}

	private static void testwrappers(){
		Integer a= Integer.valueOf(100);
		Integer b=Integer.valueOf(100);

		System.out.println(a==b);
	}

}
