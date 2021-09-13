package bobo.lambdas;



import java.util.function.LongPredicate;
import java.util.stream.LongStream;

public class StreamParallelProcessing {

    static final long STREAM_SIZE = 100_000_000;
    static final int N = 10;

    public static void main(String[] args) {
        LongPredicate isDivisibleBySeven = i -> i % 7 ==0;

        System.out.println("Sequential processing");

        performNTimes(N, () -> LongStream.range(1,STREAM_SIZE)
                .filter(isDivisibleBySeven)
                .count());

        System.out.println("Parallel processing");
        performNTimes(N, () -> LongStream.range(1, STREAM_SIZE)
                .parallel()
                .filter(isDivisibleBySeven)
                .count() );

    }

    static void performNTimes(int n, Runnable r){
        long start=System.nanoTime();
        LongStream.range(1, n).forEach(value -> r.run());
        long finish=System.nanoTime();
        long timeWork=(finish-start)/1000_000;
        System.out.println("time work: " +timeWork + " msec");
    }
}
