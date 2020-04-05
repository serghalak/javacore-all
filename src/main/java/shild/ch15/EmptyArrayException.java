package shild.ch15;


public class EmptyArrayException extends Exception {

    public EmptyArrayException(String s) {
        super("array is empty");
    }
}
