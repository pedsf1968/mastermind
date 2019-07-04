package com.ocr.pedsf.vue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Resultat {
   private static final Logger log = LogManager.getLogger(Resultat.class.getName());
   /**
    * display : affichage du résultat de la partie
    * @param gagnant nom du gagnant
    * @param perdant nom du perdant
    * @param coup nombre de coups
    * @param code code recherché
    */
   public static void display(String gagnant, String perdant, int coup, String code){
      log.traceEntry();
      System.out.println("\nMASTERMIND");

      if(coup==1) {
         System.out.println(gagnant + " a gagné contre " + perdant + " en " + coup + " coup !");
         log.trace(gagnant + " a gagné contre " + perdant + " en " + coup + " coup !");
      } else {
         System.out.println(gagnant + " a gagné contre " + perdant + " en " + coup + " coups !");
         log.trace(gagnant + " a gagné contre " + perdant + " en " + coup + " coups !");
      }

      System.out.println("Le code à trouver était : " + code);
      log.traceExit("Le code à trouver était : " + code);
   }
}
