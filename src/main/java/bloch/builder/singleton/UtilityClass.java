package bloch.builder.singleton;

import sun.jvm.hotspot.utilities.AssertionFailure;

public class UtilityClass {


    //non instance class
    private UtilityClass(){
        throw new AssertionError();
    }
}


