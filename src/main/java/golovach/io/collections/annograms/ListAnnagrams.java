package golovach.io.collections.annograms;

import java.util.*;

public class ListAnnagrams {

    public static void main(String[] args) {
        ReadDictionary readDictionary =new ReadDictionary();
        SortAnagrams sortAnagrams = new SortAnagrams();
        Set<String> strings = readDictionary.readFromFile("./uk_UA.dic", "/");
        System.out.println(strings.size());
        //System.out.println(strings);
        Map<AnnagramString, Set<String>> sortAnnagram = sortAnagrams.getSortAnnagram(strings);

        List<Map.Entry<AnnagramString,Set<String>>> list=new LinkedList<>(sortAnnagram.entrySet());

        Collections.sort(list,(o1, o2) -> o2.getValue().size()-o1.getValue().size());
        System.out.println(list);

        for (Map.Entry<AnnagramString,Set<String>> entry : list){
            if(entry.getValue().size()>1){
                System.out.println(entry.getKey().getWord());
                System.out.println(entry.getValue());
                System.out.println("-------------------------------------");
            }
        }
//        Collections.sort(sortAnnagram, new Comparator<Map.Entry<AnnagramString,Set<String>>>() {
//
//            @Override
//            public int compare(Map.Entry<AnnagramString, Set<String>> o1, Map.Entry<AnnagramString, Set<String>> o2) {
//                return 0;
//            }
//        });
//
//        for (Map.Entry<AnnagramString,Set<String>>entry:sortAnnagram.entrySet()){
//            if(entry.getValue().size()>1){
//                System.out.println(entry.getKey().getWord());
//                System.out.println(entry.getValue());
//                System.out.println("-------------------------------------");
//            }
//        }


        System.out.println(sortAnnagram.size());
    }


}
