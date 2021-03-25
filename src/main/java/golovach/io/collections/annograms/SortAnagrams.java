package golovach.io.collections.annograms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SortAnagrams {

    private Map<AnnagramString, Set<String>> sortAnnagram=new HashMap<>();

    public Map<AnnagramString, Set<String>> getSortAnnagram(Set<String>strings){


        int count=0;

        for (String str : strings){
            count++;


            AnnagramString annagramString=new AnnagramString(str);

            if(sortAnnagram.containsKey(annagramString)){
                Set<String> setStrings = sortAnnagram.get(annagramString);
                setStrings.add(str);
            }else{
                Set<String>setStrings=new HashSet<>();
                setStrings.add(str);
                sortAnnagram.put(annagramString,setStrings );
            }
            if (count>20000){

                break;
            }
        }



        return sortAnnagram;
    }

}
