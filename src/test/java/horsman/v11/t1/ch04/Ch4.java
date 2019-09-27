package horsman.v11.t1.ch04;

public class Ch4 {

	private static int nextId;
	private int id;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int k;
		Person p1=new Person("Serg",45);
		Person p2=new Person("Vova", 22);
		swap(p1,p2);
		//System.out.println("nextId="+nextId+"	id="+k);
		System.out.println("p1="+p1);
		System.out.println("p2="+p2);
	}
	
	private static void swap(Person p1, Person p2){
		p1=p2;
		p2=new Person("Misha",18);		
	}

}
