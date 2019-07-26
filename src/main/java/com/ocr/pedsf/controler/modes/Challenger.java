package com.ocr.pedsf.controler.modes;

import com.ocr.pedsf.Main;
import com.ocr.pedsf.model.*;
import com.ocr.pedsf.model.actors.Actor;
import com.ocr.pedsf.model.actors.ActorFactory;
import com.ocr.pedsf.vue.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Challenger : class implémentant Mode qui sert de contrôleur pour le modes Challenger
 *
 * @author pedsf
 * @version 1.0
 */
public class Challenger implements Mode {
   private static final Logger log = LogManager.getLogger(Challenger.class);

   private MastermindProperties mp;
   private int nbCoup = 0;


   public Challenger() {
      if(Main.getMp()!=null)
      this.mp = Main.getMp();
   }


   public void run() {
      log.traceEntry();
      boolean trouve;

      log.info("MASTERMIND : Mode Challenger\n");

      // initialisation des protagonistes
      Actor robot = ActorFactory.get(ActorFactory.ACTOR_ROBOT, mp.getRobot1Name(),mp);
      Actor utilisateur = ActorFactory.get(ActorFactory.ACTOR_USER, mp.getUserName(),mp);

      if (mp.isDebugMode())
         log.info("(Combinaison secrète : {})\n",robot.getNs().getNombre());

      if(mp.getLength() == 1) {
         log.info("Trouvez un nombre à 1 chiffre.\n");
      } else {
         log.info("Trouvez un nombre à {} chiffres.\n", mp.getLength());
      }

      do {
         // l'utilisateur attaque l'ordinateur
         trouve = utilisateur.attack(robot);
         nbCoup++;
      } while (!trouve && nbCoup < mp.getTrials());

      if (nbCoup < mp.getTrials()) {
         Result.display(utilisateur.getNom(), robot.getNom(), nbCoup, robot.getNs().getNombre());
      } else {
         Result.display(robot.getNom(), utilisateur.getNom(), nbCoup, robot.getNs().getNombre());
      }

      log.traceExit();
   }
}
