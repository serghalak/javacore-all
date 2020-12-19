package golovach.io.collections.annograms;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ReadDictionary {

    private Set<String>dictionary=new HashSet<>();

    public Set<String>readFromFile(String fileName,String splitString){
        BufferedReader reader=null;
        String line=null;
        try {
            reader=new BufferedReader(new FileReader(fileName));
            while((line=reader.readLine())!=null){
                String addWord=getWord(line,splitString);
                if(addWord!=null){
                    dictionary.add(addWord);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    private String getWord(String row,String splitSimvol){
        String[] split = row.split(splitSimvol);
        if(split.length>1 && split[1].equals("efg")){
            return split[0];
        }else{
            return null;
        }
    }


}
