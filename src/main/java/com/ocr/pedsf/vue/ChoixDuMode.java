package com.ocr.pedsf.vue;

import com.ocr.pedsf.Main;
import com.ocr.pedsf.model.MastermindProperties;
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

   /**
    * get : méthode générale pour demander le choix du mode de jeu
    *
    * @return réponse de l'utilisateur sous forme d'entier
    */
   public static int get() {
      log.traceEntry();
      MastermindProperties mp = Main.getMp();

      String message = "MASTERMIND\n\n" +
            "Choisissez le modes de jeu :\n" +
            "0 - Choix du nombre de digit (" +
            mp.getLongueur() + ")\n" +
            "1 - Challenger\n" +
            "2 - Défenseur\n" +
            "3 - Duel\n" +
            "4 - AutoBaston\n\n" +
            "Saisissez un nombre plus grand pour quitter.\n";
      String errorMessage = "Erreur de saisie!\nChoisissez un nombre positif.\n";

      return log.traceExit(getIntPositif(message, errorMessage));
   }




}
