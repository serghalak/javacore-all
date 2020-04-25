package shild.ch15;

public class InstanceMethWithObjectRefDemo {

	public static <T> int filtr(T[] arrHighTemp
			, MyFunc<T>func, T item ){

		int count=0;
		
		for(int i=0 ; i<arrHighTemp.length;i++){
			if(func.func(arrHighTemp[i],item)){
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HighTemp[] weekDayHighs = {
				new HighTemp(89), new HighTemp(82),
				new HighTemp(90), new HighTemp(89),
				new HighTemp(89), new HighTemp(91),
				new HighTemp(84), new HighTemp(83) };
		
		int count=filtr(weekDayHighs,HighTemp::lessThanTemp, new HighTemp(90));
		System.out.println("count: " + count);

		Number number;
		System.out.println("count="+Integer.toBinaryString(2_000_000_000));
		System.out.println(Long.numberOfTrailingZeros(2_000_000_000));
		System.out.println(Integer.parseInt("10FFFF",16));
		System.out.println(Math.exp(2));
		System.out.println(Math.log(2.7));
	}

}
