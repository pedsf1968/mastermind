package com.ocr.pedsf.controler.modes;

import com.ocr.pedsf.Main;
import com.ocr.pedsf.model.GameProperties;
import com.ocr.pedsf.model.actors.Actor;
import com.ocr.pedsf.model.actors.ActorFactory;
import com.ocr.pedsf.vue.Result;
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

   private GameProperties mp;
   private int nbCoup = 0;

   public Duel(){
      if(Main.getMp()!=null) {
         this.mp = Main.getMp();
      }
   }

   /**
    * run : méthode qui lance le duel
    */
   public void run(){
      log.traceEntry();
      boolean trouve;

      log.info("MASTERMIND : Mode Duel\n");

      // initialisation des protagonistes
      Actor robot = ActorFactory.get(ActorFactory.ACTOR_ROBOT,mp.getRobot1Name(),mp);
      Actor utilisateur = ActorFactory.get(ActorFactory.ACTOR_USER,mp.getUserName(),mp);
      // demande à l'utilisateur son NombreSecret
      utilisateur.setNs();

      if (mp.isDebugMode())
         log.info("(Combinaison secrète : {})\n",robot.getNs().getNombre());

      if(mp.getLength() == 1) {
         log.info("Trouvez un nombre à 1 chiffre.");
      } else {
         log.info("Trouvez un nombre à {} chiffres.", mp.getLength());
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
         Result.display(utilisateur.getNom(), robot.getNom(), nbCoup, robot.getNs().getNombre());
      } else {
         Result.display(robot.getNom(), utilisateur.getNom(), nbCoup, robot.getNs().getNombre());
      }

      log.traceExit();
   }
}
