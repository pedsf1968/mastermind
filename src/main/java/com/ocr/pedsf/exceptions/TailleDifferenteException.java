package com.ocr.pedsf.exceptions;

public class TailleDifferenteException extends Exception {
   public TailleDifferenteException(){
      super("On ne peut pas comparer des nombres de taille différente !");
   }
}
