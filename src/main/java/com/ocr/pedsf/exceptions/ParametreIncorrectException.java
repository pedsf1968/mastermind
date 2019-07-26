package com.ocr.pedsf.exceptions;

/**
 * ParametreIncorrectException : exception lancée si l'utilisateur entre
 *                            un mauvais paramètre en ligne de commande
 *
 * @author PEDSF
 * @version 1.0
 */
public class ParametreIncorrectException extends Exception {
   public ParametreIncorrectException(){
      super("Le paramètre est incorrect !");
   }

   public ParametreIncorrectException(String message){
      super(message);
   }
}
