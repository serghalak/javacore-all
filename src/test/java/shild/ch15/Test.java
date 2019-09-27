package shild.ch15;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyNumber myNumber;
		
		myNumber=()->123.45;
		
//		myNumber=new MyNumber() {
//			
//			@Override
//			public double getValue() {
//				// TODO Auto-generated method stub
//				return 123.456;
//			}
//		};
		
		System.out.println(myNumber.getValue());
	}

}
