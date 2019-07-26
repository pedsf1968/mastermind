package com.ocr.pedsf.vue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * AskProposition : ask a code proposition
 *  *               the get() method use
 *  *               display() to diplay the message
 *  *               and ask() to get right  answer
 *
 * @author pedsf
 * @version 1.0
 */
public class AskProposition extends Ask {
   private static final Logger log = LogManager.getLogger(AskProposition.class.getName());

   public AskProposition() {
      throw new UnsupportedOperationException();
   }

   /**
    * get : méthode générale pour demander une proposition d'un code à l'utilisateur
    *       elle pause la question avec display()
    *       et demande à l'utilisateur avec ask() jusqu'à ce que la réponse soit correcte
    *
    * @param digit taille du code à trouver
    * @param isDebugMode activation du mode debug
    * @return code saisi par l'utilisateur
    */
   public static String get(int digit, boolean isDebugMode){
      log.traceEntry();

      String message = "Choisissez un nombre de ";
      String errorMessage = "Mauvaise saisie !\n";
      String pattern = "[0-9]{" + digit + "}";

      if(digit==1) {
         message += "1 chiffre.";
         errorMessage += "Choisissez un nombre à 1 chiffre.";
      } else {
         message += digit + " chiffres.";
         errorMessage += "Choisissez un nombre à "+ digit +" chiffres.\n";
      }

      return log.traceExit(getString(message,errorMessage,pattern,isDebugMode));
   }
}
