package com.ocr.pedsf.vue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * DemandeReponse : class d'affichage pour demander une réponse de la proposition de l'adversaire
 *               on appel la class par la méthode get() qui se sert
 *               de display() pour afficher la question
 *               et de ask() pour lire les entrées clavier
 *               et retourne une réponse valide
 *
 * @author pedsf
 * @version 1.0
 */
public class DemandeReponse extends Mode{
   private static final Logger log = LogManager.getLogger(DemandeReponse.class.getName());

   private DemandeReponse() {
      throw new UnsupportedOperationException();
   }

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

      return log.traceExit(getString(message, errorMessage,digit,pattern,isDebugMode));
   }

}
