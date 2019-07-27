package com.ocr.pedsf.vue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * AskCodeLength : ask the new lenght for the code
 *  *               the get() method use
 *  *               display() to diplay the message
 *  *               and ask() to get right  answer
 *
 * @author pedsf
 * @version 1.0
 */
public class AskCodeLength extends Ask {
   private static final Logger log = LogManager.getLogger(AskCodeLength.class.getName());

   /**
    * get : méthode générale pour demander un nombre de digit pour le code
    *       elle pause la question avec display()
    *       et demande à l'utilisateur avec ask() jusqu'à ce que la réponse soit correcte
    *
    * @param max nombre de digit maximum que l'utilisateur peut spécifier
    * @return nouvelle taille du code souhaité
    */
   public static int get(int max){
      log.traceEntry();

      String message = "Choisissez le nombre de digit entre 1 et " + max + " : ";
      String errorMessage = "Erreur de saisie!\n";

      int response = getInt(message,errorMessage,1,max);

      if (response == 1)
         log.info("Modification de la taille du nombre secret à {} digit.\n",response);
      else
         log.info("Modification de la taille du nombre secret à {} digits.\n",response );

      return log.traceExit(response);
   }
}
