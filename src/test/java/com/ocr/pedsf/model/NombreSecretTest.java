package com.ocr.pedsf.model;

import com.ocr.pedsf.exceptions.CaractereIncorrectException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.codes.NombreSecret;
import org.junit.Test;

import static org.junit.Assert.*;

public class NombreSecretTest {

   @Test
   public void Given_taille_When_initNombreTailleN_Then_getAStringNumber(){
      NombreSecret n = new NombreSecret(5);
      assertEquals(n.getTaille(), 5);
      n = new NombreSecret(20);
      assertEquals(n.getTaille(), 20);
   }

   @Test
   public void Given_twoNombreSecret_When_test_Then_getRightAnswer(){
      NombreSecret n1 = new NombreSecret("1234");

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
      NombreSecret n1 = new NombreSecret("1234");
      NombreSecret n2 = new NombreSecret("12345");

      try {
         n1.test(n2);
      } catch (CaractereIncorrectException ciE) {
         ciE.printStackTrace();
      }

   }

   @Test(expected = CaractereIncorrectException.class)
   public void Given_NombreSecretAndStringContainBadCaracter_When_test_getException() throws CaractereIncorrectException {
      NombreSecret n1 = new NombreSecret("1234");
      try {
         n1.test("12d4");
      } catch (TailleDifferenteException e) {
         e.printStackTrace();
      }
   }
}