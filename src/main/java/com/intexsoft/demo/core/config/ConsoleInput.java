package com.intexsoft.demo.core.config;

import com.intexsoft.demo.utils.Pair;

import java.io.InputStream;
import java.util.Scanner;

public enum ConsoleInput {
    INSTANCE(System.in);
    private final Scanner scanner;

    ConsoleInput(InputStream is) {
        scanner = new Scanner(is);
    }

    public Pair<String, String[]> readCommand() {
        var inputString = scanner.nextLine();
        var strings = inputString.split(" ");
        var params = extractParams(strings);

        return new Pair<>(strings[0], params);
    }

    private String[] extractParams(String[] split) {
        if (split.length > 1) {
            var params = new String[split.length - 1];
            System.arraycopy(split, 1, params, 0, split.length - 1);
            return params;
        }

        return new String[]{};
    }

    public void destroy() {
        scanner.close();
    }
}
