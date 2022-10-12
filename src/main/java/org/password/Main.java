package org.password;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final PasswordGenerator PASSWORD_GENERATOR = new PasswordGenerator();
    private static final PasswordVerifier PASSWORD_VERIFIER = new PasswordVerifier();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Хотите сгенерировать пароль" +
                "\n\tyes - да, no - нет");

        String response = in.nextLine(), password = null;
        if (Objects.equals(response, "yes")) {
            password = PASSWORD_GENERATOR.passwordGenerate();
        } else if (Objects.equals(response, "no")) {
            do {
                System.out.print("\nПридумайте пароль. " +
                        "\nВведите его здесь -> ");
                password = in.nextLine();
            } while (!PASSWORD_VERIFIER.passwordVerify(password));
        } else {
            System.exit(1);
        }

        System.out.println("\nВаш пароль - " + password);
    }
}