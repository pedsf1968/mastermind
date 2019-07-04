package com.ocr.pedsf.vue;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ResultatTest {
   private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

   @Test
   public void Given_score_When_askDisplayScore_Then_getRightMessage(){
      System.setOut(new PrintStream(outContent));

      Resultat.display("gagnant","perdant",3, "1234");

      String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
      assertEquals("MASTERMIND", output[1]);
      assertEquals("gagnant a gagné contre perdant en 3 coups !", output[2]);
      assertEquals("Le code à trouver était : 1234", output[3]);
   }

   @Test
   public void Given_scoreWith1coup_When_askDisplayScore_Then_getRightMessage(){
      System.setOut(new PrintStream(outContent));

      Resultat.display("gagnant","perdant",1, "1234");

      String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
      assertEquals("MASTERMIND", output[1]);
      assertEquals("gagnant a gagné contre perdant en 1 coup !", output[2]);
      assertEquals("Le code à trouver était : 1234", output[3]);
   }

}