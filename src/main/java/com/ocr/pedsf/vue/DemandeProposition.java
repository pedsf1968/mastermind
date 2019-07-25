package com.ocr.pedsf.vue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * DemandeProposition : class d'affichage pour demander une proposition de nombre secret au joueur
 *               on appel la class par la méthode get() qui se sert
 *               de display() pour afficher la question
 *               et de ask() pour lire les entrées clavier
 *               et retourne une réponse valide
 *
 * @author pedsf
 * @version 1.0
 */
public class DemandeProposition extends Mode{
   private static final Logger log = LogManager.getLogger(DemandeProposition.class.getName());

   public DemandeProposition() {
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
