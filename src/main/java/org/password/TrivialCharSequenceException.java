package org.password;

class TrivialCharSequenceException extends Exception {
    private final String m_charSequence;

    TrivialCharSequenceException(String message, String charSequence) {
        super(message);
        m_charSequence = charSequence;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + m_charSequence;
    }
}