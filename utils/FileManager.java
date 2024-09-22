package utils;

import data.Texts;
import data.Alphabet;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager  {
    private static final int AB_S_RUS_LENGTH = Alphabet.ALPHABET_LENGTH;
// чтение
    public static ArrayList<char[]> readBigFile(Path file) {
        ArrayList<char[]> listOfCharLines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                listOfCharLines.add(line.toCharArray());
                }

            System.out.println(Texts.SUCCESS_READ);
        } catch (IOException e) {
            System.out.println(Texts.ERROR_READ_BIG_FILE);
        }
        return listOfCharLines;
    }

    //создание файла
    public static void createFile(Path file) {
        try {
            Files.createFile(file);
            System.out.println(Texts.SUCCESS_CREATED);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //шифрование файла
    public static char[] cipherCharArrayMechanism(char[] sourceCharArray, int key, boolean keyPlus) { //KeyPlus - true - шифруем, false - дешифруем
        char[] decryptedCharArray = new char[sourceCharArray.length];
        for (int i = 0; i < sourceCharArray.length; i++) {
            if (Alphabet.ALPHABET.contains(Character.toLowerCase(sourceCharArray[i]))) {
                int origIndex = Alphabet.ALPHABET.indexOf(Character.toLowerCase(sourceCharArray[i]));
                int destinationIndex = keyPlus ?
                        ((origIndex + key) < AB_S_RUS_LENGTH ? (origIndex + key) : (origIndex + key) % AB_S_RUS_LENGTH) :
                        ((origIndex - key) >= 0 ? (origIndex - key) : AB_S_RUS_LENGTH + origIndex - key);
                decryptedCharArray[i] = Alphabet.ALPHABET.get(destinationIndex);
            } else
                decryptedCharArray[i] = '-';
        }
        return decryptedCharArray;
    }

    //дешифрование файла
    public static char[] decipherCharArrayMechanism(char[] sourceCharArray, int key, boolean keyPlus) { //KeyPlus - true - шифруем, false - дешифруем
        char[] decryptedCharArray = new char[sourceCharArray.length];
        for (int i = 0; i < sourceCharArray.length; i++) {
            if (Alphabet.ALPHABET.contains(Character.toLowerCase(sourceCharArray[i]))) {
                int origIndex = Alphabet.ALPHABET.indexOf(Character.toLowerCase(sourceCharArray[i]));
                int destinationIndex = keyPlus ?
                        ((origIndex + key) < AB_S_RUS_LENGTH ? (origIndex + key) : (origIndex + key) % AB_S_RUS_LENGTH) :
                        ((origIndex - key) >= 0 ? (origIndex - key) : AB_S_RUS_LENGTH + origIndex - key);
                decryptedCharArray[i] = Alphabet.ALPHABET.get(destinationIndex);
            } else
                decryptedCharArray[i] = '-';
        }
        return decryptedCharArray;
    }


    // запись
    public static void writeToBigFile(Path file, ArrayList<char[]> sourseCharArrayList) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file)) {
            for (char[] line : sourseCharArrayList) {
                bufferedWriter.write(line);
            }
            System.out.println(Texts.SUCCESS_ENCRYPTION_TEXT + file.toAbsolutePath());
        } catch (IOException e) {
            System.out.println(Texts.ERROR_WRITE_BIG_FILE);
        }
    }

}
