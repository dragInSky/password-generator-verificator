package org.password;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class PasswordGenerator {
    public String passwordGenerate() {
        String aplhLower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specs = "!\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~.\"";
        //генерация данных для генерации пароля
        PasswordData passwordData = new PasswordData();

        //строки с символами пароля
        StringBuilder charsForPassword = new StringBuilder();

        //добавление в пароль нужного количества всех символов...
        addChars(charsForPassword, passwordData.getAlphaLowerCase(), aplhLower);
        addChars(charsForPassword, passwordData.getAlphaUpperCase(), aplhLower.toUpperCase());
        addChars(charsForPassword, passwordData.getNums(), digits);
        addChars(charsForPassword, passwordData.getSpecs(), specs);

        String password = charsForPassword.toString();
        do {
            //лист строк, где каждая строка - это один символ строки password
            List<String> list = Arrays.asList(password.split(""));

            //перемешивание всех символов листа
            Collections.shuffle(list);

            //возвращение из листа результирующей строки (пароля)
            password = list.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(""));

            //перемешиваем пароль, пока в он не станет соответствовать требованиям
        } while(!(new PasswordVerifier()).passwordVerify(password));

        return password;
    }

    //метод, который добавляет в StringBuilder определенное количество заданных символов
    private static void addChars(StringBuilder charsForPassword, int countToAdd, String charSeq) {
        for (; countToAdd > 0; --countToAdd) {
            charsForPassword.append(charSeq.charAt(ThreadLocalRandom.current().nextInt(0, charSeq.length())));
        }
    }
}