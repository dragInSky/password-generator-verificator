package org.password;

public class PasswordVerifier {
    public static boolean passwordVerify(String passwordToVerify) {
        //объединяем все функции проверки на корректность пароля
        return accordedSymbols(passwordToVerify) & notTrivial(passwordToVerify) & allowedSymbols(passwordToVerify);
    }

    private static boolean accordedSymbols(String passwordToVerify) {
        boolean res = true;

        try {
            if (!(Req.MIN_SYMBOLS.get() <= passwordToVerify.length() && Req.MAX_SYMBOLS.get() >= passwordToVerify.length())) {
                throw new PasswordLengthException("Длина введенного вами пароля не соответствует требованиям " +
                        "по минимальному и максимальному количеству символов соответственно ",
                        Req.MIN_SYMBOLS.get(), Req.MAX_SYMBOLS.get());
            }
        } catch (PasswordLengthException e) {
            res = false;
            System.out.println(e.getMessage());
        }

        try {
            if (!checker(passwordToVerify, Req.MIN_ALPHA_LOWER.get(), Enum.a.get(), Enum.z.get())) {
                throw new SymbolsCountException("Введенный вами парольне не содержит нужного количества букв латинского " +
                        "алфавита в нижнем регистре: ",
                        Req.MIN_ALPHA_LOWER.get());
            }
        } catch (SymbolsCountException e) {
            res = false;
            System.out.println(e.getMessage());
        }

        try {
            if (!checker(passwordToVerify, Req.MIN_ALPHA_UPPER.get(), Enum.A.get(), Enum.Z.get())) {
                throw new SymbolsCountException("Введенный вами парольне не содержит нужного количества букв латинского " +
                        "алфавита в верхнем регистре: ",
                        Req.MIN_ALPHA_UPPER.get());
            }
        } catch (SymbolsCountException e) {
            res = false;
            System.out.println(e.getMessage());
        }

        try {
            if (!checker(passwordToVerify, Req.MIN_NUM.get(), Enum.ZERO.get(), Enum.NINE.get())) {
                throw new SymbolsCountException("Введенный вами парольне не содержит нужного количества цифр: ",
                        Req.MIN_NUM.get());
            }
        } catch (SymbolsCountException e) {
            res = false;
            System.out.println(e.getMessage());
        }

        try {
            if (!checker(passwordToVerify, Req.MIN_SPEC.get(),
                    Enum.MARK.get(), Enum.SLASH.get(),
                    Enum.TWO_DOT.get(), Enum.DOG.get(),
                    Enum.START_ARR.get(), Enum.APOS.get(),
                    Enum.START_FIGURE.get(), Enum.TILDA.get())) {
                throw new SymbolsCountException("Введенный вами парольне не содержит нужного количества спец-символов: ",
                        Req.MIN_SPEC.get());
            }
        } catch (SymbolsCountException e) {
            res = false;
            System.out.println(e.getMessage());
        }

        return res;
    }

    private static boolean notTrivial(String passwordToVerify) {
        for (int i = 0, count1 = 0, count2 = 0; i < passwordToVerify.length() - 1; ++i) {
            //если символы идут друг за другом в таблице кодов
            if (Math.abs(passwordToVerify.charAt(i) - passwordToVerify.charAt(i + 1)) == 1) {
                ++count1;
            } else {
                count1 = 0;
            }

            //если символы совпадают
            if (passwordToVerify.charAt(i) - passwordToVerify.charAt(i + 1) == 0) {
                ++count2;
            } else {
                count2 = 0;
            }

            try {
                if (count1 == 2) {
                    throw new TrivialCharSequenceException("Введенный вами пароль содержит последовательность трех или " +
                            "более последовательно идущих друг за другом символов: ",
                            "" + passwordToVerify.charAt(i - 1) + passwordToVerify.charAt(i) + passwordToVerify.charAt(i + 1));
                }
            } catch (TrivialCharSequenceException e) {
                System.out.println(e.getMessage());
            }

            try {
                if (count2 == 2) {
                    throw new TrivialCharSequenceException("Введенный вами пароль содержит последовательность трех или " +
                            "более подряд идущих друг за другом символов: ",
                            passwordToVerify.substring(i, i + 1).repeat(3));
                }
            } catch (TrivialCharSequenceException e) {
                System.out.println(e.getMessage());
            }
        }

        return true;
    }

    private static boolean allowedSymbols(String passwordToVerify) {
        //проверка, все ли символы в пароле принадлежат латинским буквам, цифрам или определенным спец-символам
        boolean bFlag = passwordToVerify.chars()
                .allMatch(c -> compare(c,
                        Enum.a.get(), Enum.z.get(),
                        Enum.A.get(), Enum.Z.get(),
                        Enum.ZERO.get(), Enum.NINE.get(),
                        Enum.MARK.get(), Enum.SLASH.get(),
                        Enum.TWO_DOT.get(), Enum.DOG.get(),
                        Enum.START_ARR.get(), Enum.APOS.get(),
                        Enum.START_FIGURE.get(), Enum.TILDA.get()));

        try {
            if (!bFlag) {
                throw new SimpleException("В пароле должны содержать только символы латинского алфавита, цифры и спец-символы (см. ниже)\n" +
                        "\t! \" # $ % & ' ( ) * + , - . / : ; < = > ? @ [ \\ ] ^ _` { | } ~.");
            }
        } catch (SimpleException e) {
            System.out.println(e.getMessage());
        }

        return bFlag;
    }

    private static boolean checker(String str, int req, int... args) {
        return req <= str.chars()
                .filter(c -> {
                    boolean res = false;
                    for (int i = 0; i < args.length - 1; i += 2) {
                        res = res || compare(c, args[i], args[i + 1]);
                    }
                    return res;
                })
                .count();
    }

    private static boolean compare(int c, int... args) {
        boolean res = false;
        for (int i = 0; i < args.length - 1; i += 2) {
            res = res || (args[i] <= c && c <= args[i + 1]);
        }
        return res;
    }
}