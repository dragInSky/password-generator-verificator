package org.password;

class TrivialCharSequenceException extends Exception {
    private final String charSequence;

    TrivialCharSequenceException(String message, String charSequence) {
        super(message);
        this.charSequence = charSequence;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + charSequence;
    }
}