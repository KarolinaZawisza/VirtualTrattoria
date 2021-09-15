package com.company.utils;

import com.company.entity.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUtils {

    private ConsoleUtils() {
        throw new IllegalStateException("ConsoleUtils cannot be instantiated.");
    }

    public static String readStringFromUser() {
        BufferedReader bufferedConsoleReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            return bufferedConsoleReader.readLine();
        } catch (IOException exception) {
            writeToConsole("Encountered problem when reading from console. Caused by: " + exception.getClass() + ": " + exception.getLocalizedMessage());
        }

        return null;
    }

    public static void writeToConsole(String message) {
        System.out.println(message);
    }
}
