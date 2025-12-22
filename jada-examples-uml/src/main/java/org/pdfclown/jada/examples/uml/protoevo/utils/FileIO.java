/*
  SPDX-FileCopyrightText: © 2023 Dylan Cope

  SPDX-License-Identifier: MIT

  Changes: package renamed for jada-examples-uml
*/
package org.pdfclown.jada.examples.uml.protoevo.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Dylan Cope
 */
public class FileIO {
  public static void appendLine(String filePath, String line) {
    try {
      Files.write(Paths.get(filePath),
          (line + "\n").getBytes(),
          StandardOpenOption.APPEND);
    } catch (IOException ignored) {
    }
  }

  public static Object load(String filename) throws IOException, ClassNotFoundException {
    FileInputStream fileIn = new FileInputStream(filename + ".dat");
    ObjectInputStream in = new ObjectInputStream(fileIn);
    Object result = in.readObject();
    in.close();
    fileIn.close();
    return result;
  }

  public static void save(Object object, String filename) {
    try {
      FileOutputStream fileOut =
          new FileOutputStream(filename + ".dat");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(object);
      out.close();
      fileOut.close();
      System.out.println("Serialized data saved to:" + filename + ".dat");
    } catch (IOException i) {
      i.printStackTrace();
    }
  }

}
