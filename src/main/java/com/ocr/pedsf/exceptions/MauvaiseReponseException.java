package com.ocr.pedsf.exceptions;

public class MauvaiseReponseException extends Exception {
   public MauvaiseReponseException(){
      super("Vous avez saisi une mauvaise réponse !");
   }
   public MauvaiseReponseException(String message){
      super(message);
   }
}
