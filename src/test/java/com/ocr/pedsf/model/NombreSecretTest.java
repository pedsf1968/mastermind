package com.ocr.pedsf.model;

import com.ocr.pedsf.exceptions.CaractereIncorrectException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.codes.SimplifiedCode;
import org.junit.Test;

import static org.junit.Assert.*;

public class NombreSecretTest {

   @Test
   public void Given_taille_When_initNombreTailleN_Then_getAStringNumber(){
      SimplifiedCode n = new SimplifiedCode(5);
      assertEquals(5, n.getTaille());
      n = new SimplifiedCode(20);
      assertEquals(20,n.getTaille() );
   }

   @Test
   public void Given_twoNombreSecret_When_test_Then_getRightAnswer(){
      SimplifiedCode n1 = new SimplifiedCode("1234");

      try {
         assertEquals("====",n1.test("1234"));
         assertEquals("-=--",n1.test("4278"));
         assertEquals("-=+=",n1.test("2214"));
      } catch (TailleDifferenteException | CaractereIncorrectException e) {
         e.printStackTrace();
      }

  }

   @Test (expected = TailleDifferenteException.class)
   public void Given_twoNombreSecretWithDifferentLength_When_test_getException() throws TailleDifferenteException {
      SimplifiedCode n1 = new SimplifiedCode("1234");
      SimplifiedCode n2 = new SimplifiedCode("12345");

      try {
         n1.test(n2);
      } catch (CaractereIncorrectException ciE) {
         ciE.printStackTrace();
      }

   }

   @Test(expected = CaractereIncorrectException.class)
   public void Given_NombreSecretAndStringContainBadCaracter_When_test_getException() throws CaractereIncorrectException {
      SimplifiedCode n1 = new SimplifiedCode("1234");
      try {
         n1.test("12d4");
      } catch (TailleDifferenteException e) {
         e.printStackTrace();
      }
   }
}