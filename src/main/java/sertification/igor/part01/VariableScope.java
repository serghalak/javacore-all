package sertification.igor.part01;

import java.util.Arrays;
import java.util.List;

public  class VariableScope {

    private static int age;
    private String name;

    static{
        age=0;
    }


    public VariableScope() {
        this.name = "";
        this.age = 0;
    }

    public static void main(String[] args) {
        VariableScope variableScope=new VariableScope();
        System.out.println(variableScope.someMethod(10, "Serg"));
        sortArray(new int[]{27, 10, 33, 12, 7, 44, 3, 88, 57, 4});
        secondMaxElement(new int[]{27, 10, 33, 12, 7, 44, 3, 88, 57, 4});
    }

    private int someMethod(int age, String name){
        //String name=null;
        //int age=0;
        //int i = Integer.parseInt(0455);
        //List
        //String  str;
        //return age;
        System.out.println(name);
        return 0;
    }

    private static void sortArray(int[] intArray){

        for(int i=0; i<intArray.length; i++){
            for (int j=i+1; j<intArray.length;j++){
                if(intArray[i]>intArray[j]){
                    int temp=intArray[i];
                    intArray[i]=intArray[j];
                    intArray[j]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(intArray));
    }

    private static void secondMaxElement(int[] intArr){
        int max=intArr[0];
        int max1=Integer.MIN_VALUE;

        for(int i=1;i<intArr.length;i++){
            if(intArr[i]>max){
                max1=max;
                max=intArr[i];
            }else if(intArr[i]>max1){
                max1=intArr[i];
            }
        }
        System.out.println("secondMax " + max1);
    }
}
