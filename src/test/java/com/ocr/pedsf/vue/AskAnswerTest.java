package com.ocr.pedsf.vue;

import com.ocr.pedsf.model.GameProperties;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class AskAnswerTest {
   private GameProperties properties = new GameProperties();

   @Test
   public void Given_code_When_get_Then_getSameCode(){
      AskAnswer.setMp(properties);
      System.setIn(new ByteArrayInputStream("+-+-=\n".getBytes()));
      assertEquals("+-+-=", AskAnswer.get(5,false));
   }

}