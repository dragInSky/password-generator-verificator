package org.password;

import java.util.concurrent.ThreadLocalRandom;

class PasswordData {
    public int alphaLowerCaseToGenerate, alphaUpperCaseToGenerate, numToGenerate, specToGenerate;

    PasswordData() {
        //генерация длины пароля
        int passwordLength = ThreadLocalRandom.current().nextInt(Req.MIN_SYMBOLS.get(), Req.MAX_SYMBOLS.get() + 1);

        //осталось использовать символов пароля
        int tmpCurrentLength = passwordLength - Req.MIN_ALPHA_LOWER.get() - Req.MIN_ALPHA_UPPER.get() - Req.MIN_NUM.get() - Req.MIN_SPEC.get();

        alphaLowerCaseToGenerate = ThreadLocalRandom.current().nextInt(Req.MIN_ALPHA_LOWER.get(), Req.MIN_ALPHA_LOWER.get() + tmpCurrentLength + 1);
        tmpCurrentLength -= alphaLowerCaseToGenerate - Req.MIN_ALPHA_LOWER.get();

        alphaUpperCaseToGenerate = ThreadLocalRandom.current().nextInt(Req.MIN_ALPHA_UPPER.get(), Req.MIN_ALPHA_UPPER.get() + tmpCurrentLength + 1);
        tmpCurrentLength -= alphaUpperCaseToGenerate - Req.MIN_ALPHA_UPPER.get();

        numToGenerate = ThreadLocalRandom.current().nextInt(Req.MIN_NUM.get(), Req.MIN_NUM.get() + tmpCurrentLength + 1);

        specToGenerate = passwordLength - alphaLowerCaseToGenerate - alphaUpperCaseToGenerate - numToGenerate;
    }
}