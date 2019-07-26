package com.ocr.pedsf.vue;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ResultatTest {
   private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

   @Test
   public void Given_score_When_askDisplayScore_Then_getRightMessage(){
      System.setOut(new PrintStream(outContent));

      Result.display("gagnant","perdant",3, "1234");

      String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
      /*assertEquals("MASTERMIND : Résultat", output[0]);
      assertEquals("gagnant a gagné contre perdant en 3 coups !", output[2]);
      assertEquals("Le code à trouver était : 1234", output[4]);*/
   }

   @Test
   public void Given_scoreWith1coup_When_askDisplayScore_Then_getRightMessage(){
      System.setOut(new PrintStream(outContent));

      Result.display("gagnant","perdant",1, "1234");

    /*  String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
      assertEquals("MASTERMIND : Résultat", output[0]);
      assertEquals("gagnant a gagné contre perdant en 1 coup !", output[2]);
      assertEquals("Le code à trouver était : 1234", output[4]);*/
   }

}