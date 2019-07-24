package com.ocr.pedsf.vue;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class ChoixDuModeTest {

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
}
