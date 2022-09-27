package org.password;

//перечисление с кодами всех символов
public enum Enum {
    a('a'), z('z'),
    A('A'), Z('Z'),
    ZERO('0'), NINE('9'),
    MARK('!'), SLASH('/'),
    TWO_DOT(':'), DOG('@'),
    START_ARR('['), APOS('`'),
    START_FIGURE('{'), TILDA('~');

    private final int code;

    Enum(int code) {
        this.code = code;
    }

    public int get() {
        return code;
    }
}