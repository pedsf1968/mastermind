package com.ocr.pedsf.controler.modes;

import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.personnages.Personnage;
import com.ocr.pedsf.model.personnages.Robot;
import com.ocr.pedsf.vue.Resultat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Autobaston : class implémentant Mode qui gère le combat entre deux IA
 *
 * @author PEDSF
 * @version 1.0
 */
public class Autobaston implements Mode {
   private static final Logger log = LogManager.getLogger(Duel.class);

   private MastermindProperties mp;
   private int nbCoup = 0;

   public Autobaston(MastermindProperties mp) {
      this.mp = mp;
   }

   @Override
   public void run() {
      log.traceEntry();
      boolean trouve;

      log.info("MASTERMIND : Mode AutoBaston\n");

      // initialisation des protagonistes
      Personnage chapi = new Robot(mp.getNomRobot1(),mp);
      Personnage chapo = new Robot(mp.getNomRobot2(),mp);

      // initialisation des NombresSecrets
      chapi.init();
      chapo.init();

      if (mp.isDebugMode()) {
         log.info("(Combinaison secrète {} : {} )\n", chapi.getNom(),chapi.getNs().getNombre());
         log.info("(Combinaison secrète {} : {})\n", chapo.getNom(),chapo.getNs().getNombre());
      }

      do {
         // Chapi attaque Chapo
         trouve = chapi.attack(chapo);

         if(trouve) {
            Resultat.display(chapi.getNom(), chapo.getNom(), nbCoup, chapo.getNs().getNombre());
         } else {
            // au tour de Chapo
            trouve = chapo.attack(chapi);
            if(trouve)
               Resultat.display(chapo.getNom(), chapi.getNom(), nbCoup, chapi.getNs().getNombre());
         }

         nbCoup++;
      } while (!trouve);

      log.traceExit();
   }
}
