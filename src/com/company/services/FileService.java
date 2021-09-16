package com.company.services;

import com.company.repository.OrderDatabase;
import com.company.utils.ConsoleUtils;

import java.io.*;

public class FileService {
    public void writeOrderDatabaseToFile(OrderDatabase orderDatabase, String fileName) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(orderDatabase);
        } catch (IOException e) {
            ConsoleUtils.writeToConsole("Cannot write database to the file.");
        }
    }

    public OrderDatabase readOrderDatabaseFromFile(String fileName) {
        try (
                FileInputStream fileInputStream = new FileInputStream(fileName);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            return (OrderDatabase) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            ConsoleUtils.writeToConsole("Cannot read database from the file.");
        }
        return null;
    }
}
