package com.ocr.pedsf.vue;

import com.ocr.pedsf.exceptions.BornageException;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;

import static org.junit.Assert.*;

public class ChoixDuModeTest {

   @Test
   public void Given_number0_When_get_Then_get0(){
      System.setIn(new ByteArrayInputStream("0\n".getBytes()));
      assertEquals(0,ChoixDuMode.get());
   }

   @Test
   public void Given_number3_When_get_Then_get3(){
      System.setIn(new ByteArrayInputStream("3\n".getBytes()));
      assertEquals(3,ChoixDuMode.get());
   }

   @Test
   public void Given_number11_When_get_Then_get11(){
      System.setIn(new ByteArrayInputStream("11\n".getBytes()));
      assertEquals(11,ChoixDuMode.get());
   }

   @Test
   public void Given_nothing_When_askMode_Then_displayMenu(){
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outContent));
      ChoixDuMode.display();

      String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
      assertEquals("MASTERMIND", output[1]);
      assertEquals("Choisissez le modes de jeu :", output[3]);
      assertEquals("0 - Choix du nombre de digit", output[4]);
      assertEquals("1 - Challenger", output[5]);
      assertEquals("2 - Défenseur", output[6]);
      assertEquals("3 - Duel", output[7]);
      assertEquals("4 - AutoBaston", output[8]);
      assertEquals("Saisissez un nombre plus grand pour quitter", output[10]);
   }

   @Test
   public void Given_number0_When_ask_Then_get0(){
      System.setIn(new ByteArrayInputStream("0\n".getBytes()));
      try {
         assertEquals(0,ChoixDuMode.ask());
      } catch (BornageException e) {
         e.printStackTrace();
      }
   }

   @Test
   public void Given_number3_When_ask_Then_get3(){
      System.setIn(new ByteArrayInputStream("3\n".getBytes()));
      try {
         assertEquals(3,ChoixDuMode.ask());
      } catch (BornageException e) {
         e.printStackTrace();
      }
   }

   @Test
   public void Given_number11_When_ask_Then_get11(){
      System.setIn(new ByteArrayInputStream("11\n".getBytes()));
      try {
         assertEquals(11,ChoixDuMode.ask());
      } catch (BornageException e) {
         e.printStackTrace();
      }
   }

   @Test(expected = BornageException.class)
   public void Given_negatifNumber_When_ask_Then_getError() throws BornageException{
      System.setIn(new ByteArrayInputStream("-1\n".getBytes()));
      ChoixDuMode.ask();
   }


   @Test(expected = InputMismatchException.class)
   public void Given_character_When_ask_Then_getError() throws InputMismatchException{
      System.setIn(new ByteArrayInputStream("dsf\n".getBytes()));
      try {
         ChoixDuMode.ask();
      } catch (BornageException e) {
         e.printStackTrace();
      }
   }
}