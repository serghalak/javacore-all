package horsman14.v2ch01.gathering;

import module java.base;

class ImplementingGatherers {
    void main() {
        IO.println(IntStream.range(1, 1000).boxed().parallel().gather(sampling(50)).toList());
    }
    static class IntermediateState { 
        long index = -1; 
    }
    <T> Gatherer<T, IntermediateState, T> sampling(int n) {
        return Gatherer.ofSequential(IntermediateState::new, 
                (state, e, downstream) -> {
                    state.index++;
                    if (state.index % n == 0) 
                        return downstream.push(e);
                    else
                        return true;
                    });
    }
}


