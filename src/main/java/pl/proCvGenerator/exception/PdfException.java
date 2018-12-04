package pl.proCvGenerator.exception;

public class PdfException extends Exception {

    private String message;

    public PdfException(String message) {
        this.message = message;
    }

    public PdfException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
