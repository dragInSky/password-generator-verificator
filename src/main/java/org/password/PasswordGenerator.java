package org.password;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class PasswordGenerator {
    //строка с символами пароля
    private static final StringBuilder charsForPassword = new StringBuilder();
    public String passwordGenerate() {
        String aplhLower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specs = "!\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~.\"";
        //генерация данных для генерации пароля
        PasswordData passwordData = new PasswordData();

        //добавление в пароль нужного количества всех символов...
        addChars(passwordData.getAlphaLowerCase(), aplhLower);
        addChars(passwordData.getAlphaUpperCase(), aplhLower.toUpperCase());
        addChars(passwordData.getNums(), digits);
        addChars(passwordData.getSpecs(), specs);

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
    private static void addChars(int countToAdd, String charSeq) {
        for (; countToAdd > 0; --countToAdd) {
            charsForPassword.append(charSeq.charAt(ThreadLocalRandom.current().nextInt(0, charSeq.length())));
        }
    }
}