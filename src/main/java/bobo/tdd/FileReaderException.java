package bobo.tdd;



public class FileReaderException extends RuntimeException {

    private Exception exception;
    public FileReaderException(String errorMessage, Exception e) {
        super(errorMessage,e);
    }
}
