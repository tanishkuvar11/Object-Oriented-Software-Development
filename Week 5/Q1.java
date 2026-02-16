/*Design and implement a Java application that copies the contents of one file 
to another using byte streams. The program must use FileInputStream to 
read data from a source file and FileOutputStream to write the same data to 
a destination file */

import java.io.*;
import java.util.*;

public class Q1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file path: ");
        String dir = sc.next();

        try {
            FileInputStream f1 = new FileInputStream(dir);
            OutputStream f2 = new FileOutputStream("outputFruit.txt");

            int i;

            while ((i = f1.read()) != -1) {
                f2.write((char) i);
            }

            System.out.println("File sucessfully copied to outputFruit.txt");
            f1.close();
            f2.close();

        }

        catch (IOException e) {
            System.out.println("File could not open as: " + e);
        }

    }
}
