package com.ocr.pedsf;

import com.ocr.pedsf.model.MastermindProperties;

public class Main {
   private static String CONFIGURATION_FILE = "src/main/resources/mastermind.properties";

   public static void main(String[] args){
      MastermindProperties mp = new MastermindProperties(CONFIGURATION_FILE);

      System.out.println("Hello !" + mp);
   }

}
