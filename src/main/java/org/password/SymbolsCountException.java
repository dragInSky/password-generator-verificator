package org.password;

class SymbolsCountException extends Exception {
    private final int req;

    SymbolsCountException(String message, int req) {
        super(message);
        this.req = req;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + req;
    }
}