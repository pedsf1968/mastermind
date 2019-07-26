package com.ocr.pedsf.vue;

import com.ocr.pedsf.Main;
import com.ocr.pedsf.model.MastermindProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * AskGameMode : ask the mode choice
 *  *            the get() method use
 *  *            display() to diplay the message
 *  *            and ask() to get right  answer
 *
 * @author pedsf
 * @version 1.0
 */
public class AskGameMode extends Ask {
   static final Logger log = LogManager.getLogger(AskGameMode.class);
   private static MastermindProperties mp;

   public static void setMp(MastermindProperties mp) {
      AskGameMode.mp = mp;
   }

   /**
    * get : méthode générale pour demander le choix du mode de jeu
    *
    * @return réponse de l'utilisateur sous forme d'entier
    */
   public static int get() {
      log.traceEntry();
      if(Main.getMp()!=null)
         mp = Main.getMp();

      String message = "MASTERMIND\n\n" +
            "Choisissez le modes de jeu :\n" +
            "0 - Choix du nombre de digit (" +
            mp.getLength() + ")\n" +
            "1 - Challenger\n" +
            "2 - Défenseur\n" +
            "3 - Duel\n" +
            "4 - AutoBaston\n\n" +
            "Saisissez un nombre plus grand pour quitter.\n";
      String errorMessage = "Erreur de saisie!\nChoisissez un nombre positif.\n";

      return log.traceExit(getIntPositif(message, errorMessage));
   }




}
