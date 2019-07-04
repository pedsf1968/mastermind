package com.ocr.pedsf.vue;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;

import static org.junit.Assert.*;

public class DemandePropositionTest {

   @Test
   public void Given_number_When_get_Then_getSameNumber(){
      System.setIn(new ByteArrayInputStream("1234\n".getBytes()));
            assertEquals("1234",DemandeProposition.get(4));
   }

   @Test
   public void Given_5_When_askNombreDigit_Then_displayMenu(){
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outContent));
      DemandeProposition.display(5);

      String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
      assertEquals("Choisissez un nombre de 5 chiffre(s).", output[0]);
   }

   @Test
   public void Given_number_When_ask_Then_getSameNumber(){
      System.setIn(new ByteArrayInputStream("1234\n".getBytes()));
      assertEquals("1234",DemandeProposition.ask(4));
   }

   @Test(expected = InputMismatchException.class)
   public void Given_character_When_ask_Then_getError() throws InputMismatchException{
      System.setIn(new ByteArrayInputStream("dsf\n".getBytes()));
     DemandeProposition.ask(4);
   }

   @Test(expected = InputMismatchException.class)
   public void Given_shortNumber_When_ask_Then_getError() throws InputMismatchException{
      System.setIn(new ByteArrayInputStream("123\n".getBytes()));
      DemandeProposition.ask(4);
   }

   @Test(expected = InputMismatchException.class)
   public void Given_longNumber_When_ask_Then_getError() throws InputMismatchException{
      System.setIn(new ByteArrayInputStream("123456\n".getBytes()));
      DemandeProposition.ask(4);
   }
}