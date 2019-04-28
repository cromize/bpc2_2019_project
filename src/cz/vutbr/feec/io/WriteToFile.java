package cz.vutbr.feec.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
  public static void write(File file, String data) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
      bw.write(data + "\n");
    } catch (IOException e) {
      System.out.println("\nNelze zapsat do souboru");
    }
  }

}
