package org.password;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Хотите сгенерировать пароль" +
                "\n\tyes - да, no - нет" +
                "\n-> ");

        String response = in.nextLine(), password = null;
        if (Objects.equals(response, "yes")) {
            password = (new PasswordGenerator()).passwordGenerate();
        } else if (Objects.equals(response, "no")) {
            do {
                System.out.print("\nПридумайте пароль: " +
                        "\n -> ");
                password = in.nextLine();
            } while (!(new PasswordVerifier()).passwordVerify(password));
        } else {
            System.exit(1);
        }

        System.out.println("\nВаш пароль - " + password);
    }
}