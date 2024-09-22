import java.io.*;
import data.Alphabet;
import data.Texts;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class main {
    public static void main(String args[]) throws IOException {
        final String INPUT_PATH = "Введите путь и имя файла который нужно зашифровать: ";
        final String OUTPUT_PATH = "Введите путь и имя выходного зашифрованного файла : ";
        final String INPUT_KEY = "Введите ключ целое неотрицательное число: ";
        final String OUTPUT_PATH_DE = "Введите путь и имя выходного файла для расшифрования : ";
        //ввод ключа
        System.out.println(INPUT_KEY);
        BufferedReader readerKey = new BufferedReader(new InputStreamReader(System.in));
        String key = readerKey.readLine();
        int ikey = Integer.parseInt(key);

        //ввод пути к файлу который нужно зашифровать
        System.out.println(INPUT_PATH);
        BufferedReader readerPathIn = new BufferedReader(new InputStreamReader(System.in));
        String pathIn = readerPathIn.readLine();
        Path fileIn = Path.of(pathIn);

        //путь и имя выходного зашифрованного файла
        System.out.println(OUTPUT_PATH);
        BufferedReader readerPathOut = new BufferedReader(new InputStreamReader(System.in));
        String pathOut = readerPathOut.readLine();
        Path fileOut = Path.of(pathOut);

        //путь и имя выходного расшифрованного файла
        System.out.println(OUTPUT_PATH_DE);
        BufferedReader readerPathOutDe = new BufferedReader(new InputStreamReader(System.in));
        String pathOutDe = readerPathOutDe.readLine();
        Path fileOutDe = Path.of(pathOutDe);

        // чтение и шифрование
        ArrayList<char[]> listOfCharLines = utils.FileManager.readBigFile(fileIn);
        ArrayList<char[]> resultCharArray = new ArrayList<>();
        //ArrayList<char[]> resultCharArrayDe = new ArrayList<>();
        for (char[] line : listOfCharLines) {
            resultCharArray.add(utils.FileManager.cipherCharArrayMechanism(line,ikey,true));
            //resultCharArrayDe.add(utils.FileManager.cipherCharArrayMechanism(line,ikey,false));
        }

            // создание файлов
            utils.FileManager.createFile(fileOut);
            //utils.FileManager.createFile(fileOutDe);

            // запись файла
            utils.FileManager.writeToBigFile(fileOut, resultCharArray);
            //utils.FileManager.writeToBigFile(fileOutDe, resultCharArrayDe);

            // чтение зашифрованного файла и дешифрование
            ArrayList<char[]> listOfCharLinesDe = utils.FileManager.readBigFile(fileOut);
            ArrayList<char[]> resultCharArrayDe = new ArrayList<>();
            for (char[] lineDe : listOfCharLinesDe) {
            //resultCharArray.add(utils.FileManager.cipherCharArrayMechanism(line,ikey,true));
            resultCharArrayDe.add(utils.FileManager.cipherCharArrayMechanism(lineDe,ikey,false));
        }

        // создание файлов
        utils.FileManager.createFile(fileOutDe);

        // запись файла
        utils.FileManager.writeToBigFile(fileOutDe, resultCharArrayDe);

    }
}
