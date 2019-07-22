package com.ocr.pedsf.exceptions;

/**
 * MauvaiseReponseException : exception lancée si l'utilisateur fait une mauvaise saisie
 *
 * @author PEDSF
 * @version 1.0
 */
public class MauvaiseReponseException extends Exception {
   public MauvaiseReponseException(){
      super("Vous avez saisi une mauvaise réponse !");
   }
   public MauvaiseReponseException(String message){
      super(message);
   }
}
