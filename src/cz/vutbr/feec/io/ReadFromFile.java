package cz.vutbr.feec.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile {
  public static String read(File file) {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      return br.readLine();
    } catch (IOException e) {
      System.out.println("\nNelze cist ze souboru");
    }
    return null;
  }

}
