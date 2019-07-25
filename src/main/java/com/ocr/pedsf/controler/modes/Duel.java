package com.ocr.pedsf.controler.modes;

import com.ocr.pedsf.Main;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.actors.Actor;
import com.ocr.pedsf.model.actors.ActorFactory;
import com.ocr.pedsf.model.actors.Robot;
import com.ocr.pedsf.model.actors.User;
import com.ocr.pedsf.vue.Resultat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Duel : class  implémentant Mode qui sert de contrôleur pour le modes Duel
 *
 * @author pedsf
 * @version 1.0
 */
public class Duel implements Mode {
   private static final Logger log = LogManager.getLogger(Duel.class);

   private MastermindProperties mp;
   private int nbCoup = 0;

   public Duel(){
      this.mp = Main.getMp();
   }

   /**
    * run : méthode qui lance le duel
    */
   public void run(){
      log.traceEntry();
      boolean trouve;

      log.info("MASTERMIND : Mode Duel\n");

      // initialisation des protagonistes
      Actor robot = ActorFactory.get(ActorFactory.robotActor,mp.getNomRobot1(),mp);
      Actor utilisateur = ActorFactory.get(ActorFactory.userActor,mp.getNomUser(),mp);
      // initialisation des NombresSecrets
      utilisateur.init();

      if (mp.isDebugMode())
         log.info("(Combinaison secrète : {})\n",robot.getNs().getNombre());

      if(mp.getLongueur() == 1) {
         log.info("Trouvez un nombre à 1 chiffre.");
      } else {
         log.info("Trouvez un nombre à {} chiffres.", mp.getLongueur());
      }

      do {
         // l'utilisateur attaque l'ordinateur
         trouve = utilisateur.attack(robot);

         // au tour de l'ordinateur si la réponse n'est pas bonne
         if(!trouve)
            trouve = robot.attack(utilisateur);
         nbCoup++;
      } while (!trouve);

      if (robot.getNs().equals(utilisateur.getNsToSearch())) {
         Resultat.display(utilisateur.getNom(), robot.getNom(), nbCoup, robot.getNs().getNombre());
      } else {
         Resultat.display(robot.getNom(), utilisateur.getNom(), nbCoup, robot.getNs().getNombre());
      }

      log.traceExit();
   }
}
