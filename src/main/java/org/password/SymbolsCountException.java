package org.password;

class SymbolsCountException extends Exception {
    private final int m_req;

    SymbolsCountException(String message, int req) {
        super(message);
        m_req = req;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + m_req;
    }
}