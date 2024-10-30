package org.player.exceptions;

public class NoPlayerDataException extends RuntimeException {
    private final String message;

    public NoPlayerDataException(Exception e) {
        super(e);
        this.message = e.getMessage();
    }

    public NoPlayerDataException(String msg, Exception e) {
        super(e);
        this.message = msg;
    }

    public NoPlayerDataException(String msg) {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
