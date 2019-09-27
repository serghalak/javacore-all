package horsman.v11.t1.ch05;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public class Employee implements Cloneable{
	private String name;
	   private double salary;
	   private LocalDate hireDay;
	   
	   String strPackage="package";
	   protected String strProtected="protected";
	   public String strPublic="public";
	   
	   String getPackage(){
		   return "package";
	   }
	   protected String getProtected(){
		   return "protected";
	   }
	   public String getPublic(){
		   return "public";
	   }

	   public Employee(String name, double salary, int year, int month, int day)
	   {
	      this.name = name;
	      this.salary = salary;
	      hireDay = LocalDate.of(year, month, day);
	   }

	   public String getName()
	   {
	      return name;
	   }

	   public double getSalary()
	   {
	      return salary;
	   }

	   public LocalDate getHireDay()
	   {
	      return hireDay;
	   }

	   public void raiseSalary(double byPercent)
	   {
	      double raise = salary * byPercent / 100;
	      salary += raise;
	   }

	   public boolean equals(Object otherObject)
	   {
	      // a quick test to see if the objects are identical
	      if (this == otherObject) return true;

	      // must return false if the explicit parameter is null
	      if (otherObject == null) return false;

	      // if the classes don't match, they can't be equal
	      System.out.println(otherObject.getClass().getName());
	      System.out.println(otherObject.getClass());
	      if (getClass() != otherObject.getClass()) return false;

	      // now we know otherObject is a non-null Employee
	      Employee other = (Employee) otherObject;

	      // test whether the fields have identical values
	      return Objects.equals(name, other.name) 
	         && salary == other.salary && Objects.equals(hireDay, other.hireDay);
	   }

	   public int hashCode()
	   {
	      return Objects.hash(name, salary, hireDay); 
	   }

	   public String toString()
	   {
	      return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" 
	         + hireDay + "]";
	   }
	   

	public void setName(String name) {
		this.name = name;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setHireDay(LocalDate hireDay) {
		this.hireDay = hireDay;
	}

	@Override
	public Employee clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		//Employee cloned=(Employee)super.clone();
		//cloned.hireDay=getHireDay().c
		//Comparator c;
		
		return (Employee)super.clone();
	} 
	   
	   
}
