package com.ocr.pedsf.vue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * DemandeReponse : class d'affichage du résultat
 *
 * @author pedsf
 * @version 1.0
 */

public class Resultat {
   private static final Logger log = LogManager.getLogger(Resultat.class.getName());

   private Resultat() {
      throw new UnsupportedOperationException();
   }

   /**
    * display : affichage du résultat de la partie
    * @param gagnant nom du gagnant
    * @param perdant nom du perdant
    * @param coup nombre de coups
    * @param code code recherché
    */
   public static void display(String gagnant, String perdant, int coup, String code){
      log.traceEntry();
      log.info("MASTERMIND : Résultat\n");

      if(coup==1) {
         log.info("{} a gagné contre {} en 1 coup !\n",gagnant, perdant);
      } else {
         log.info("{} a gagné contre {} en {} coups !\n",gagnant, perdant, coup);
      }

      log.info("Le code à trouver était : {}",code);
      log.traceExit("Le code à trouver était : " + code);
   }
}
