package com.ocr.pedsf.exceptions;

/**
 * BornageException : exception de dépassement des bornes d'un intervalle
 *
 * @author PEDSF
 * @version 1.0
 */
public class BornageException extends Exception{
   public BornageException(){
      super("Vous avez donné une mauvaise réponse à l'ordinateur !");
   }
   public BornageException(String message){
      super(message);
   }
}
