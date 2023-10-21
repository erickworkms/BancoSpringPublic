package com.spring.bancospring.scanner;

import java.util.Scanner;

public final class LerDados {

    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }
    public static String lerDado() {

        String texto = scanner.nextLine();

        return texto;
    }

}
