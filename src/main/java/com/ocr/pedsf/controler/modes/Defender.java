package com.ocr.pedsf.controler.modes;

import com.ocr.pedsf.Main;
import com.ocr.pedsf.model.GameProperties;
import com.ocr.pedsf.model.actors.Actor;
import com.ocr.pedsf.model.actors.ActorFactory;
import com.ocr.pedsf.model.codes.CodeFactory;
import com.ocr.pedsf.model.codes.MastermindCode;
import com.ocr.pedsf.model.codes.SimplifiedCode;
import com.ocr.pedsf.vue.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Defenseur : class implémentant Mode qui sert de contrôleur pour le modes Defenseur
 *
 * @author pedsf
 * @version 1.0
 */
public class Defender implements Mode {
   private static final Logger log = LogManager.getLogger(Defender.class);

   private GameProperties mp;
   private int nbCoup = 0;

   public Defender(){
      if(Main.getMp()!=null) {
         this.mp = Main.getMp();
      }
   }

   /**
    * run : méthode qui lance le modes défense
    */
   public void run(){
      log.traceEntry();
      log.info("MASTERMIND : Mode Defenseur\n");

      // initialisation des protagonistes
      Actor robot = ActorFactory.get(ActorFactory.ACTOR_ROBOT, mp.getRobot1Name(),mp);
      Actor utilisateur = ActorFactory.get(ActorFactory.ACTOR_USER, mp.getUserName(),mp);

      // demande à l'utilisateur son NombreSecret
      utilisateur.setNs();

      if(mp.isDebugMode() && mp.getGameType()== CodeFactory.CODE_SIMPLIFIED)
         log.info(SimplifiedCode.SHORTMESSAGE);
      else
         log.info(MastermindCode.SHORTMESSAGE);

      do {
         nbCoup++;
      } while (!robot.attack(utilisateur) && nbCoup<mp.getTrials());

      if (nbCoup>mp.getTrials()) {
         // l'adversaire n'a pas trouvé la solution
         Result.display(utilisateur.getNom(), robot.getNom(), nbCoup, utilisateur.getNs().getNombre());
      } else {
         // l'adversaire a trouvé la réponse dans la limite du nombre de coups
         Result.display(robot.getNom(), utilisateur.getNom(),nbCoup, utilisateur.getNs().getNombre());
      }

      log.traceExit();
   }
}
