package com.ocr.pedsf.vue;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class DemandePropositionTest {

   @Test
   public void Given_number_When_get_Then_getSameNumber(){
      System.setIn(new ByteArrayInputStream("1234\n".getBytes()));
            assertEquals("1234",DemandeProposition.get(4,false));
   }

}
