package com.telran.deadlock;

import java.io.*;

public class FileUtils {

    public static void write(File file, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(content);
            bw.newLine();
            bw.flush(); //"дожать" несколько байт текста

        } catch (IOException e) {}
    }

    public static void rewrite(File file, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            bw.write(content);
//            bw.write("\n");
//            bw.newLine();
            bw.flush();
        } catch (IOException e) {}
    }

    public static String getContent(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ( (line = br.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        } catch (IOException e) {}

        return stringBuilder.toString();
    }
}
