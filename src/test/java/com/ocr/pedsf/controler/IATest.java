package com.ocr.pedsf.controler;

import com.ocr.pedsf.exceptions.BornageException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;
import org.junit.Test;

import static org.junit.Assert.*;

public class IATest {

   @Test
   public void Given_combinaisonAndEqualResponse_When_doProposition_Then_getSameNumber() {

      IA ia = new IA("1234");
      try {
         ia.proposition("====");
      } catch (TailleDifferenteException | BornageException e) {
         e.printStackTrace();
      }
      assertEquals("1234", ia.getNombreSecret());
   }

   @Test
   public void Given_combinaison8888AndResponseUpper_When_doProposition_Then_get9999() {
      IA ia = new IA("8888");
      try {
         ia.proposition("++++");
      } catch (TailleDifferenteException | BornageException e) {
         e.printStackTrace();
      }
      assertEquals("9999", ia.getNombreSecret());
   }

   @Test
   public void Given_combinaison1111AndResponseLower_When_doProposition_Then_get0000() {
      IA ia = new IA("1111");
      try {
         ia.proposition("----");
      } catch (TailleDifferenteException | BornageException e) {
         e.printStackTrace();
      }
      assertEquals("0000",ia.getNombreSecret());
   }

   @Test
   public void Given_combinaison8181AndResponseMixedUpperLower_When_doProposition_Then_get9090() {
      IA ia = new IA("8181");
      try {
         ia.proposition("+-+-");
      } catch (TailleDifferenteException | BornageException e) {
         e.printStackTrace();
      }
      assertEquals("9090",ia.getNombreSecret());
   }

   @Test(expected = TailleDifferenteException.class)
   public void Given_combinaisonWithDifferentLength_When_doProposition_getException() throws TailleDifferenteException {
      IA ia = new IA("8181");
      try {
         ia.proposition("+-+");
      } catch (TailleDifferenteException | BornageException e) {
         e.printStackTrace();
      }
   }


}