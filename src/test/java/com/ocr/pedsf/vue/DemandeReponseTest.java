package com.ocr.pedsf.vue;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class DemandeReponseTest {

   @Test
   public void Given_code_When_get_Then_getSameCode(){
      System.setIn(new ByteArrayInputStream("+-+-=\n".getBytes()));
      assertEquals("+-+-=", AskAnswer.get(5,false));
   }

}