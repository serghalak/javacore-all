package horsman14.v2ch01.collecting;

import module java.base;

class ImplementingCollectors {
    void main() {
        IO.println(IntStream.range(1, 1000)
            .boxed()
            .parallel()
            .collect(randomSampling(0.05)));
        IO.println(IntStream.range(1, 1000)
            .boxed()
            .parallel()
            .collect(randomSampling(0.05, Collectors.toSet())));
    }
    
    <T> Collector<T, List<T>, List<T>> randomSampling(double p) {
        return Collector.of(ArrayList<T>::new, 
                (resultContainer, e) -> { 
                    if (Math.random() < p) 
                        resultContainer.add(e); 
                    }, 
                (rc1, rc2) -> { rc1.addAll(rc2); return rc1; },
                Function.identity(), 
                Collector.Characteristics.IDENTITY_FINISH);
    }
    
    <T, A, R> Collector<T, A, R> randomSampling(double p, Collector<T, A, R> downstream) {
        BiConsumer<A, T> downstreamAccumulator = downstream.accumulator();
        return Collector.of(downstream.supplier(),
                (resultContainer, e) -> { 
                    if (Math.random() < p) 
                        downstreamAccumulator.accept(resultContainer, e); 
                    },
                downstream.combiner(), 
                downstream.finisher(),
                downstream.characteristics().toArray(Collector.Characteristics[]::new));
    }
}


