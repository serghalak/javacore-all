package shild.ch07;


class Outer {	
	

	int outer_х =100;
		
	void test(){
		for(int i=0; i<10; i++){
			class Inner {
				Inner(){
					System.out.println("Constructor Inner");
				}
			
				void display (){
						System.out.println(	"вывод: outer_x"+outer_х);
				}
			}
		}
		//Inner inner = new Inner();
		//inner.display();
		
	}
}


public class InnerClassDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Outer outer=new Outer();
		outer.test();
	}

}
