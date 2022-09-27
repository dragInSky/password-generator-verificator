package org.password;

//перечисление с требованиями к паролю: длина пароля и минимальное количество всяких символом в пароле
public enum Req {
    MIN_SYMBOLS(8), MAX_SYMBOLS(32),
    MIN_ALPHA_LOWER(1),
    MIN_ALPHA_UPPER(1),
    MIN_NUM(1),
    MIN_SPEC(1);

    private final int symbols;

    Req(int symbols) {
        this.symbols = symbols;
    }

    public int get() {
        return symbols;
    }
}