package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "gio.txt";
        String content = "Giorgi magari kacia mas ar unda qebao\nmatec magari kacia mas arunda qebao";
        try(FileWriter fileWriter = new FileWriter(fileName)){
            fileWriter.write(content);
        }
        System.out.println(printFromFile((BufferedReader br) -> {
            try {
                return br.readLine() + br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }
    public static String printFromFile(BufferedReaderProcessor processor) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("gio.txt"))) {
            return processor.process(bufferedReader);
        }
    }
}