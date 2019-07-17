package com.ocr.pedsf.exceptions;

/**
 * CaractereIncorrectException : exception de caractère incorecte dans une chaîne
 *
 * @author PEDSF
 * @version 1.0
 */
public class CaractereIncorrectException extends Exception {
   public CaractereIncorrectException(){
      super("La chaine ne comporte pas que des chiffres de 0 à 9!");
   }

   public CaractereIncorrectException(String message){
      super(message);
   }
}
