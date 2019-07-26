package com.ocr.pedsf.exceptions;


/**
 * TailleDifferenteException : exception de taille différente dans la comparaison de deux chaînes
 *
 * @author PEDSF
 * @version 1.0
 */
public class TailleDifferenteException extends Exception {
   public TailleDifferenteException(){
      super("On ne peut pas comparer des nombres de taille différente !");
   }
   public TailleDifferenteException(String message){
      super(message);
   }
}
