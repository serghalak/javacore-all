package shild.ch15;


import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class LambdaExceptionDemo {
    public static void main(String[] args) {
        double values[]={1.0,2.0,3.0,4.0};
        List<Double> listDouble  =Arrays.asList(1.0,2.0,3.0,4.0);

        System.out.println(listDouble);
        DoubleSummaryStatistics doubleSummaryStatistics
                = listDouble.stream()
                .mapToDouble(val -> Double.valueOf(val))
                .summaryStatistics();
        System.out.println(doubleSummaryStatistics.getAverage());


    }
}
