package com.ocr.pedsf.vue;

import com.ocr.pedsf.exceptions.MauvaiseReponseException;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

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
   public void Given_number3_When_get_Then_get11(){
      System.setIn(new ByteArrayInputStream("11\n".getBytes()));
      assertEquals(11,ChoixDuMode.get());
   }

   @Test (expected = NoSuchElementException.class)
   public void Givent_text_When_get_Then_getMauvaiseReponseException() throws NoSuchElementException {
      System.setIn(new ByteArrayInputStream("sdfgsq\n".getBytes()));
      int reponse = ChoixDuMode.get();
   }
}