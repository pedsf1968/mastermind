package com.ocr.pedsf.vue;

import com.ocr.pedsf.exceptions.BornageException;
import org.apache.logging.log4j.LogManager;
import org.junit.Rule;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import java.util.InputMismatchException;

import static com.ocr.pedsf.vue.Mode.*;
import static org.junit.Assert.*;

public class ModeTest {

   @Rule
   public LogAppenderResource appender = new LogAppenderResource(LogManager.getLogger(Mode.class));


   @Test
   public void Given_Message_When_display_Then_displayTheRightMessage(){
      String message = "message 13654";
      display(message);

      assertEquals(message, appender.getOutput());
   }

   @Test
   public void Given_minMaxAndNumberInRange_When_askInteger_GetTheNumber(){

      try {
         System.setIn(new ByteArrayInputStream("4\n".getBytes()));
         assertEquals(4,ask(0,10));
      } catch (BornageException e) {
         e.printStackTrace();
      }
   }

   @Test( expected = BornageException.class)
   public void Given_minMaxAndNumberOutOfRange_When_askInteger_GetBornageException()throws BornageException {
      System.setIn(new ByteArrayInputStream("11\n".getBytes()));
      assertEquals(11,ask(0,10));
   }

   @Test( expected = InputMismatchException.class)
   public void Given_minMaxAndNotNumber_When_askInteger_GetInputMismatchException()throws InputMismatchException {
      System.setIn(new ByteArrayInputStream("zer\n".getBytes()));
      try {
         assertEquals(4,ask(0,10));
      } catch (BornageException e) {
         e.printStackTrace();
      }
   }


   @Test
   public void Given_MinAndNumber_When_askInteger_GetTheNumber(){

      try {
         System.setIn(new ByteArrayInputStream("4\n".getBytes()));
         assertEquals(4,ask(0));
      } catch (BornageException e) {
         e.printStackTrace();
      }
   }

   @Test( expected = BornageException.class)
   public void Given_minAndNumberOutOfRange_When_askInteger_GetBornageException()throws BornageException {
      System.setIn(new ByteArrayInputStream("-11\n".getBytes()));
      assertEquals(-11,ask(0));
   }

   @Test( expected = InputMismatchException.class)
   public void Given_minAndNotNumber_When_askInteger_GetInputMismatchException()throws InputMismatchException {
      System.setIn(new ByteArrayInputStream("zer\n".getBytes()));
      try {
         assertEquals(0,ask(0));
      } catch (BornageException e) {
         e.printStackTrace();
      }
   }

   @Test
   public void Given_digitAndPattern_When_askString_GetString(){
      System.setIn(new ByteArrayInputStream("6544\n".getBytes()));
      assertEquals("6544",ask("[0-9]{4}"));
   }

   @Test( expected = InputMismatchException.class)
   public void Given_digitPatternAndBadString_When_askString_GetException()throws InputMismatchException {
      System.setIn(new ByteArrayInputStream("65445\n".getBytes()));
      assertEquals("65445",ask("[0-9]{4}"));
   }

   @Test( expected = InputMismatchException.class)
   public void Given_digitPatternAndNotStringNumber_When_askString_GetException()throws InputMismatchException {
      System.setIn(new ByteArrayInputStream("zer\n".getBytes()));
      assertEquals("65445",ask("[0-9]{4}"));
   }

}
