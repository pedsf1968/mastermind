package com.ocr.pedsf.vue;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;

import static org.junit.Assert.*;

public class DemandeReponseTest {

   @Test
   public void Given_code_When_get_Then_getSameCode(){
      System.setIn(new ByteArrayInputStream("+-+-=\n".getBytes()));
      assertEquals("+-+-=",DemandeReponse.get(5,false));
   }

   @Test
   public void Given_nothing_When_askDemandeReponse_Then_displayMenu(){
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outContent));
      DemandeReponse.display();

      String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
      assertEquals("Indiquez pour chaque chiffre si le résultat est + grand - petit = égal.", output[0]);
   }


   @Test
   public void Given_code_When_ask_Then_getSameCode(){
      System.setIn(new ByteArrayInputStream("+-+-=\n".getBytes()));
      assertEquals("+-+-=",DemandeReponse.ask(5));
   }

   @Test(expected = InputMismatchException.class)
   public void Given_badSign_When_ask_Then_getError() throws InputMismatchException{
      System.setIn(new ByteArrayInputStream("1dsf\n".getBytes()));
      DemandeReponse.ask(4);
   }

   @Test(expected = InputMismatchException.class)
   public void Given_shortSign_When_ask_Then_getError() throws InputMismatchException{
      System.setIn(new ByteArrayInputStream("+-\n".getBytes()));
      DemandeReponse.ask(4);
   }

   @Test(expected = InputMismatchException.class)
   public void Given_longSign_When_ask_Then_getError() throws InputMismatchException{
      System.setIn(new ByteArrayInputStream("+-+=-=+\n".getBytes()));
      DemandeReponse.ask(4);
   }

}