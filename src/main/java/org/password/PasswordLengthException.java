package org.password;

class PasswordLengthException extends Exception {
    private final int m_req1, m_req2;

    PasswordLengthException(String message, int req1, int req2) {
        super(message);
        m_req1 = req1;
        m_req2 = req2;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + m_req1 + ", " + m_req2;
    }
}