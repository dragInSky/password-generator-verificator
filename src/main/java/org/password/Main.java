package org.password;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String password = null, grats = "введите:" +
                "\n\tyes - чтобы сгенерировать пароль" +
                "\n\tno - чтобы придумать свой" +
                "\n\texit - чтобы закрыть программу" +
                "\n-> ";

        boolean bFlag = true;
        while (bFlag) {
            bFlag = false;
            System.out.print(grats);

            switch (in.nextLine()) {
                case "yes" -> password = (new PasswordGenerator()).passwordGenerate();
                case "no" -> {
                    do {
                        System.out.print("\nПридумайте пароль: " +
                                "\n -> ");
                        password = in.nextLine();
                    } while (!(new PasswordVerifier()).passwordVerify(password));
                }
                case "exit" -> System.exit(0);
                default -> {
                    grats = "\nПовторите ввод!" +
                        "\n\tyes - чтобы сгенерировать пароль" +
                        "\n\tno - чтобы придумать свой" +
                        "\n\texit - чтобы закрыть программу" +
                        "\n-> ";
                    bFlag = true;
                }
            }
        }

        System.out.println("\nВаш пароль - " + password);
    }
}