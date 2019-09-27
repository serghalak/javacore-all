package shild.ch19;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Comparator<String>cLN=(aStr,bStr)->{
			int start_aLN=aStr.lastIndexOf(' ')+1;
			int start_bLN=bStr.lastIndexOf(' ')+1;
			//System.out.println(aStr.substring(start_aLN));
			return aStr.substring(start_aLN).compareTo(bStr.substring(start_bLN));
		};
		
			TreeMap<String,Double> hm=new TreeMap<>(
					cLN.thenComparing((aStr,bStr)->aStr.compareTo(bStr))
					);
			hm.put("Джoн Доу", new Double(3434.34));
			hm.put("Toм Смит", new Double(123.22));
			hm.put("Джейн Бейкер", new Double(1378.00));
			hm.put ( "Тод Холл", new Double ( 99.22));
			hm.put("Paльф Смит", new Double(-19.08));	
			
			//System.out.println(hm);
			for(Map.Entry<String,Double>entry : hm.entrySet()){
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
	}

}
