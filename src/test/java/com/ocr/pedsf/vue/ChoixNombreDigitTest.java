package com.ocr.pedsf.vue;

import com.ocr.pedsf.exceptions.BornageException;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;

import static org.junit.Assert.*;

public class ChoixNombreDigitTest {
/*
   @Test
   public void Given_number1_When_get_Then_get1(){
      System.setIn(new ByteArrayInputStream("1\n".getBytes()));
      assertEquals(1,ChoixNombreDigit.get(10));
   }

   @Test
   public void Given_number3_When_get_Then_get3(){
      System.setIn(new ByteArrayInputStream("3\n".getBytes()));
      assertEquals(3,ChoixNombreDigit.get(10));
   }

   @Test
   public void Given_5_When_askNombreDigit_Then_displayMenu(){
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outContent));
      ChoixNombreDigit.display(5);

      String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
     // assertEquals("Choisissez le nombre de digit entre 1 et 5 : ", output[0]);
   }


   @Test
   public void Given_number1_When_ask_Then_get1(){
      System.setIn(new ByteArrayInputStream("1\n".getBytes()));
      assertEquals(1,ChoixNombreDigit.get(10));
   }

   @Test
   public void Given_number3_When_ask_Then_get3(){
      System.setIn(new ByteArrayInputStream("3\n".getBytes()));
      assertEquals(3,ChoixNombreDigit.get(10));
   }


   @Test(expected = BornageException.class)
   public void Given_negatifNumber_When_ask_Then_getError() throws BornageException{
      System.setIn(new ByteArrayInputStream("-1\n".getBytes()));
      ChoixNombreDigit.ask(4);
   }

   @Test(expected = BornageException.class)
   public void Given_outOfRangeNumber_When_ask_Then_getError() throws BornageException{
      System.setIn(new ByteArrayInputStream("6\n".getBytes()));
      ChoixNombreDigit.ask(4);
   }

   @Test(expected = InputMismatchException.class)
   public void Given_character_When_ask_Then_getError() throws InputMismatchException{
      System.setIn(new ByteArrayInputStream("dsf\n".getBytes()));
      try {
         ChoixNombreDigit.ask(4);
      } catch (BornageException e) {
         e.printStackTrace();
      }
   }*/
}