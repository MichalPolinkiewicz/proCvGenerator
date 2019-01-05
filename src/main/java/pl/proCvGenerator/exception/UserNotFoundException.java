package pl.proCvGenerator.exception;

public class UserNotFoundException extends Exception {

    private String message;

    public UserNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
