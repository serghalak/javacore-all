package shild.ch15.generic;


public class MyArrayOps {
    static <T> int countMatching(T[] vals,T val){
        int count=0;
        for (int i=0;i<vals.length;i++){
            if(vals[i] == val) count++;
        }
        return count;
    }
}
