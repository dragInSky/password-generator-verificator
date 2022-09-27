package org.password;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Хотите сгенерировать пароль\n" +
                "\tyes - да, no - нет\t");

        String password;
        if (Objects.equals(in.nextLine(), "yes")) {
            password = PasswordGenerator.passwordGenerate();
        } else {
            do {
                System.out.println("\nПридумайте пароль\n");
                password = in.nextLine();
            } while (!PasswordVerifier.passwordVerify(password));
        }

        System.out.println("\nВаш пароль - " + password);
    }
}