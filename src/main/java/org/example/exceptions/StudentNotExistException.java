package org.example.exceptions;

public class StudentNotExistException extends RuntimeException {
    private final String message;

    public StudentNotExistException(Exception e) {
        super(e);
        this.message = e.getMessage();
    }

    public StudentNotExistException(String msg, Exception e) {
        super(e);
        this.message = msg;
    }

    public StudentNotExistException(String msg) {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
