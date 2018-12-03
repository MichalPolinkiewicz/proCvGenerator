package pl.proCvGenerator.exception;

public class TooMuchCharsException extends Exception{

    private String message;

    public TooMuchCharsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
