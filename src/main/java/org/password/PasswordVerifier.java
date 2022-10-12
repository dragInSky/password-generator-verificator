package org.password;

import java.util.regex.Pattern;

public class PasswordVerifier {
    public boolean passwordVerify(String passwordToVerify) {
        //объединяем все функции проверки на корректность пароля
        return accordedSymbols(passwordToVerify) & notTrivial(passwordToVerify) & allowedSymbols(passwordToVerify);
    }

    private static boolean accordedSymbols(String passwordToVerify) {
        boolean res = true;

        try {
            if (!(Req.MIN_SYMBOLS.get() <= passwordToVerify.length() &&
                    Req.MAX_SYMBOLS.get() >= passwordToVerify.length())) {
                throw new PasswordLengthException("Длина введенного вами пароля не соответствует требованиям " +
                        "по минимальному и максимальному количеству символов соответственно ",
                        Req.MIN_SYMBOLS.get(), Req.MAX_SYMBOLS.get());
            }
        } catch (PasswordLengthException e) {
            res = false;
            System.out.println(e.getMessage());
        }

        try {
            if (!match("([^a-z]*[a-z][^a-z]*){" + Req.MIN_ALPHA_LOWER.get() + ",}", passwordToVerify)) {
                throw new SymbolsCountException("Введенный вами парольне не содержит нужного количества " +
                        "букв латинского алфавита в нижнем регистре: ",
                        Req.MIN_ALPHA_LOWER.get());
            }
        } catch (SymbolsCountException e) {
            res = false;
            System.out.println(e.getMessage());
        }

        try {
            if (!match("([^A-Z]*[A-Z][^A-Z]*){" + Req.MIN_ALPHA_UPPER.get() + ",}", passwordToVerify)) {
                throw new SymbolsCountException("Введенный вами парольне не содержит нужного количества " +
                        "букв латинского алфавита в верхнем регистре: ",
                        Req.MIN_ALPHA_UPPER.get());
            }
        } catch (SymbolsCountException e) {
            res = false;
            System.out.println(e.getMessage());
        }

        try {
            if (!match("(\\D*\\d\\D*){" + Req.MIN_NUM.get() + ",}", passwordToVerify)) {
                throw new SymbolsCountException("Введенный вами парольне не содержит нужного количества цифр: ",
                        Req.MIN_NUM.get());
            }
        } catch (SymbolsCountException e) {
            res = false;
            System.out.println(e.getMessage());
        }

        try {
            if (!match("([^\\W]*[\\W_][^\\W]*){" + Req.MIN_SPEC.get() + ",}", passwordToVerify)) {
                throw new SymbolsCountException("Введенный вами парольне не содержит нужного количества " +
                        "спец-символов: ",
                        Req.MIN_SPEC.get());
            }
        } catch (SymbolsCountException e) {
            res = false;
            System.out.println(e.getMessage());
        }

        return res;
    }

    private static boolean match(String regex, String passwordToVerify) {
        return Pattern.compile(regex).matcher(passwordToVerify).matches();
    }

    private static boolean notTrivial(String passwordToVerify) {
        for (int i = 0, count1 = 0, count2 = 0; i < passwordToVerify.length() - 1; ++i) {
            //если символы идут друг за другом в таблице кодов
            count1 = Math.abs(passwordToVerify.charAt(i) - passwordToVerify.charAt(i + 1)) == 1 ? ++count1 : 0;

            //если символы совпадают
            count2 = passwordToVerify.charAt(i) - passwordToVerify.charAt(i + 1) == 0 ? ++count2 : 0;

            try {
                if (count1 == 2) {
                    throw new TrivialCharSequenceException("Введенный вами пароль содержит последовательность " +
                            "трех или более последовательно идущих друг за другом символов: ",
                            "" + passwordToVerify.charAt(i - 1) +
                                    passwordToVerify.charAt(i) + passwordToVerify.charAt(i + 1));
                }
            } catch (TrivialCharSequenceException e) {
                System.out.println(e.getMessage());
            }

            try {
                if (count2 == 2) {
                    throw new TrivialCharSequenceException("Введенный вами пароль содержит последовательность " +
                            "трех или более подряд идущих друг за другом символов: ",
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
        Pattern pattern = Pattern.compile("[\\w\\W]{" + passwordToVerify.length() + "}");
        boolean bFlag = pattern.matcher(passwordToVerify).matches();

        try {
            if (!bFlag) {
                throw new SimpleException("В пароле должны содержать только символы латинского алфавита, " +
                        "цифры и спец-символы (см. ниже)" +
                        "\n\t! \" # $ % & ' ( ) * + , - . / : ; < = > ? @ [ \\ ] ^ _ ` { | } ~ .");
            }
        } catch (SimpleException e) {
            System.out.println(e.getMessage());
        }

        return bFlag;
    }
}