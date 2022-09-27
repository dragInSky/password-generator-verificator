package org.password;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class PasswordGenerator {
    public static String passwordGenerate() {
        //генерация данных для генерации пароля
        PasswordData passwordData = new PasswordData();

        //строки с символами пароля
        StringBuilder charsForPassword = new StringBuilder();

        //добавление в пароль нужного количества букв в нижнем регистре...
        addChars(charsForPassword, passwordData.alphaLowerCaseToGenerate, Enum.a.get(), Enum.z.get(), false);
        addChars(charsForPassword, passwordData.alphaUpperCaseToGenerate, Enum.A.get(), Enum.Z.get(), false);
        addChars(charsForPassword, passwordData.numToGenerate, Enum.ZERO.get(), Enum.NINE.get(), false);

        //добавление в пароль нужного количества спец-символом,
        //т.к. спец-символы разбросаны по кодировке, для них написан отдельный обработчик, его активирует 4 параметр (boolean)
        addChars(charsForPassword, passwordData.specToGenerate, -1, -1, true);

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
        } while(!PasswordVerifier.passwordVerify(password));

        return password;
    }

    //метод, который добавляет в StringBuilder определенное количество заданных символов
    public static void addChars(StringBuilder charsForPassword, int countToAdd, int from, int to, boolean spec) {
        for (; countToAdd > 0; --countToAdd) {
            //если нужно добавить буквы или цифры
            if (!spec) {
                charsForPassword.append((char) ThreadLocalRandom.current().nextInt(from, to + 1));
                //если нужно добавить спец-символы
            } else {
                switch (ThreadLocalRandom.current().nextInt(0, 4)) {
                    case 0 ->
                            charsForPassword.append((char) ThreadLocalRandom.current().nextInt(Enum.MARK.get(), Enum.SLASH.get() + 1));
                    case 1 ->
                            charsForPassword.append((char) ThreadLocalRandom.current().nextInt(Enum.TWO_DOT.get(), Enum.DOG.get() + 1));
                    case 2 ->
                            charsForPassword.append((char) ThreadLocalRandom.current().nextInt(Enum.START_ARR.get(), Enum.APOS.get() + 1));
                    case 3 ->
                            charsForPassword.append((char) ThreadLocalRandom.current().nextInt(Enum.START_FIGURE.get(), Enum.TILDA.get() + 1));
                }
            }
        }
    }
}