package org.password;

class PasswordLengthException extends Exception {
    private final int req1, req2;

    PasswordLengthException(String message, int req1, int req2) {
        super(message);
        this.req1 = req1;
        this.req2 = req2;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + req1 + ", " + req2;
    }
}