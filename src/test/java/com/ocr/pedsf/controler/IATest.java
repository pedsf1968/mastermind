package com.ocr.pedsf.controler;

import org.junit.Test;

import static org.junit.Assert.*;

public class IATest {

   @Test
   public void Given_combinaisonAndNombreSecret_When_doProposition_Then_modifyTheNombreSecret(){

      IA ia = new IA("1234");
      ia.proposition("====");
      assertEquals("1234",ia.getNombreSecret());

      ia = new IA("8888");
      ia.proposition("++++");
      assertEquals("9999",ia.getNombreSecret());

      ia = new IA("1111");
      ia.proposition("----");
      assertEquals("0000",ia.getNombreSecret());
   }


}