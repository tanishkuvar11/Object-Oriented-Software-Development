/*
 * Design and implement a Java application that reads textual data from an
 * existing text file using FileReader and writes the same content into another
 * text file using FileWriter.
 */

import java.util.*;
import java.io.*;

public class Q2 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file path: ");
        String dir = sc.next();

        try {
            FileReader f1 = new FileReader(dir);
            FileWriter f2 = new FileWriter("techOutput.txt");

            int i;

            while ((i = f1.read()) != -1) {
                f2.write((char) i);
            }

            System.out.println("File sucessfully copied to outputTech.txt");
            f1.close();
            f2.close();

        }

        catch (IOException e) {
            System.out.println("File could not open as: " + e);
        }

    }
}