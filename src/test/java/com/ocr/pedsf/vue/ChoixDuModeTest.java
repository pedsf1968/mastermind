package com.ocr.pedsf.vue;

import com.ocr.pedsf.exceptions.BornageException;

import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;

import static org.junit.Assert.*;

public class ChoixDuModeTest {
/*
   @Test
   public void Given_number0_When_get_Then_get0(){
      System.setIn(new ByteArrayInputStream("0\n".getBytes()));
      assertEquals(0,ChoixDuMode.get());
      System.setIn(System.in);
   }

   @Test
   public void Given_number3_When_get_Then_get3(){
      System.setIn(new ByteArrayInputStream("3\n".getBytes()));
      assertEquals(3,ChoixDuMode.get());
      System.setIn(System.in);
   }

   @Test
   public void Given_number11_When_get_Then_get11(){
      System.setIn(new ByteArrayInputStream("11\n".getBytes()));
      assertEquals(11,ChoixDuMode.get());
      System.setIn(System.in);
   }

   @Test
   public void Given_nothing_When_askDisplay_Then_displayMenu(){
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();

      System.setOut(new PrintStream(outContent));
      ChoixDuMode.display();

      String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
      //String[] out = log.toString()
      System.setOut(System.out);
*/
     /* assertEquals("MASTERMIND", output[0]);
      assertEquals("Choisissez le modes de jeu :", output[2]);
      assertEquals("0 - Choix du nombre de digit", output[3]);
      assertEquals("1 - Challenger", output[4]);
      assertEquals("2 - Défenseur", output[5]);
      assertEquals("3 - Duel", output[6]);
      assertEquals("4 - AutoBaston", output[7]);
      assertEquals("Saisissez un nombre plus grand pour quitter.", output[9]);
      System.setOut(System.out);*/
   /*}

   @Test
   public void Given_number0_When_ask_Then_get0(){
      System.setIn(new ByteArrayInputStream("0\n".getBytes()));
      try {
         assertEquals(0,ChoixDuMode.ask());
      } catch (BornageException e) {
         e.printStackTrace();
      }
      System.setIn(System.in);
   }

   @Test
   public void Given_number3_When_ask_Then_get3(){
      System.setIn(new ByteArrayInputStream("3\n".getBytes()));
      try {
         assertEquals(3,ChoixDuMode.ask());
      } catch (BornageException e) {
         e.printStackTrace();
      }
      System.setIn(System.in);
   }

   @Test
   public void Given_number11_When_ask_Then_get11(){
      System.setIn(new ByteArrayInputStream("11\n".getBytes()));
      try {
         assertEquals(11,ChoixDuMode.ask());
      } catch (BornageException e) {
         e.printStackTrace();
      }
      System.setIn(System.in);
   }

   @Test(expected = BornageException.class)
   public void Given_negatifNumber_When_ask_Then_getError() throws BornageException{
      System.setIn(new ByteArrayInputStream("-1\n".getBytes()));
      ChoixDuMode.ask();
      System.setIn(System.in);
   }


   @Test(expected = InputMismatchException.class)
   public void Given_character_When_ask_Then_getError() throws InputMismatchException{
      System.setIn(new ByteArrayInputStream("dsf\n".getBytes()));
      try {
         ChoixDuMode.ask();
      } catch (BornageException e) {
         e.printStackTrace();
      }
      System.setIn(System.in);
   }*/
}
