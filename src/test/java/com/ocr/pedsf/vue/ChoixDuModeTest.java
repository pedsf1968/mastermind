package com.ocr.pedsf.vue;

import com.ocr.pedsf.model.MastermindProperties;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class ChoixDuModeTest {
   private MastermindProperties mp = new MastermindProperties();

   @Test
   public void Given_number0_When_get_Then_get0(){
      AskGameMode.setMp(mp);
      System.setIn(new ByteArrayInputStream("0\n".getBytes()));
      assertEquals(0, AskGameMode.get());
      System.setIn(System.in);
   }

   @Test
   public void Given_number3_When_get_Then_get3(){
      System.setIn(new ByteArrayInputStream("3\n".getBytes()));
      assertEquals(3, AskGameMode.get());
      System.setIn(System.in);
   }

   @Test
   public void Given_number11_When_get_Then_get11(){
      System.setIn(new ByteArrayInputStream("11\n".getBytes()));
      assertEquals(11, AskGameMode.get());
      System.setIn(System.in);
   }
}
