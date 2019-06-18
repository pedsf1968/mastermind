package com.ocr.pedsf.utils;

import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.NombreSecret;
import org.junit.Test;

import static org.junit.Assert.*;

public class NombreSecretTest {

  @Test
  public void Given_taille_When_initNombreTailleN_Then_getAStringNumber(){
     NombreSecret n = new NombreSecret(5);

     assertEquals(n.getTaille(), 5);
     assertEquals(n.getNombre().length(),5);
     n = new NombreSecret(20);
     assertEquals(n.getTaille(), 20);
     assertEquals(n.getNombre().length(),20);
  }

  @Test
   public void Given_twoNombreSecret_When_test_Then_getRightAnswer(){
     NombreSecret n1 = new NombreSecret("1234");
     NombreSecret n2 = n1;
     NombreSecret n3 = new NombreSecret("4278");
     NombreSecret n4 = new NombreSecret("2214");

     try {
        assertEquals(n1.test(n2),"====");
        assertEquals(n1.test(n3),"-=--");
        assertEquals(n1.test(n4),"-=+=");
     } catch (TailleDifferenteException e) {
        e.printStackTrace();
     }
  }

   @Test(expected = TailleDifferenteException.class)
   public void Given_twoNombreSecretWithDifferentLength_When_test_getException() throws TailleDifferenteException {
      NombreSecret n1 = new NombreSecret("1234");
      NombreSecret n2 = new NombreSecret("12345");
      n1.test(n2);
   }

}