package org.example.exceptions;

public class InvalidStudentRequestException extends RuntimeException {

    private final String message;

    public InvalidStudentRequestException(Exception e) {
        super(e);
        this.message = e.getMessage();
    }

    public InvalidStudentRequestException(String msg, Exception e) {
        super(e);
        this.message = msg;
    }

    public InvalidStudentRequestException(String msg) {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
