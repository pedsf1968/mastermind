package com.ocr.pedsf.vue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * AskAnswer : ask the answer of a proposition
 *               the get() method use
 *               display() to diplay the message
 *               and ask() to get right  answer
 *
 * @author pedsf
 * @version 1.0
 */
public class AskAnswer extends Ask {
   private static final Logger log = LogManager.getLogger(AskAnswer.class.getName());


   /**
    * get : méthode générale pour demander une réponse à la proposition de l'adversaire
    *       elle pause la question avec display()
    *       et demande à l'utilisateur avec ask() jusqu'à ce que la réponse soit correcte
    *
    * @param digit taille du code à trouver
    * @param isDebugMode activation du mode debug
    * @return réponse saisie par l'utilisateur
    */
   public static String get(int digit, boolean isDebugMode){
      log.traceEntry();
      String message = "Indiquez pour chaque chiffre si le résultat est + grand - petit = égal.";
      String errorMessage = "Mauvaise saisie !" +
            "Indiquez pour chaque chiffre de la combinaison proposée si" +
            " le chiffre de sa combinaison est :\n" +
            "plus grand par un (+), " +
            "plus petit par un (-) " +
            "identique par un (=)\n";
      String pattern = "[-+=]{" + digit + "}";

      return log.traceExit(getString(message, errorMessage,pattern,isDebugMode));
   }

}
