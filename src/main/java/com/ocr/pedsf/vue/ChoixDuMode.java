package com.ocr.pedsf.vue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ChoixDuMode : class d'affichage pour le choix du modes de jeu
 *               hérite
 *
 * @author pedsf
 * @version 1.0
 */
public class ChoixDuMode extends Mode{
   static final Logger log = LogManager.getLogger(ChoixDuMode.class);

   private ChoixDuMode() {
      throw new UnsupportedOperationException();
   }

   public static Logger getLog() {
      return log;
   }

   /**
    * get : méthode générale pour demander le choix du mode de jeu
    *
    * @return réponse de l'utilisateur sous forme d'entier
    */
   public static int get() {
      log.traceEntry();
      String message = "MASTERMIND\n\n" +
            "Choisissez le modes de jeu :\n" +
            "0 - Choix du nombre de digit\n" +
            "1 - Challenger\n" +
            "2 - Défenseur\n" +
            "3 - Duel\n" +
            "4 - AutoBaston\n\n" +
            "Saisissez un nombre plus grand pour quitter.\n";
      String errorMessage = "Erreur de saisie!\nChoisissez un nombre positif.\n";

      return log.traceExit(getIntPositif(message, errorMessage));
   }




}
