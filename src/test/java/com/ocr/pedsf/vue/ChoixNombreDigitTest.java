package com.ocr.pedsf.vue;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class ChoixNombreDigitTest {

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
   public void Given_number10_When_ask_Then_get10(){
      System.setIn(new ByteArrayInputStream("10\n".getBytes()));
      assertEquals(10,ChoixNombreDigit.get(10));
   }

}