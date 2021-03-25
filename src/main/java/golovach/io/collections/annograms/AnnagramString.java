package golovach.io.collections.annograms;

import java.util.Arrays;

public class AnnagramString {
    private String word;

    public AnnagramString(String word) {
        this.word = word;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object wordCompare) {

//        if(wordCompare == null){
//            return false;
//        }
//        AnnagramString that=null;
//        if (wordCompare instanceof AnnagramString){
//            that = (AnnagramString) wordCompare;
//        }

        AnnagramString that = (AnnagramString) wordCompare;

        char[] thisChars=this.word.toCharArray();
        char[] thatChars=that.word.toCharArray();

        Arrays.sort(thisChars);
        Arrays.sort(thatChars);
//        if(Arrays.equals(thisChars,thatChars)){
//            System.out.println(this.word+ " and " + that.word + " is " + Arrays.equals(thisChars,thatChars));
//        }
        return Arrays.equals(thisChars,thatChars);

    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
