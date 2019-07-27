package com.ocr.pedsf.controler.modes;

import com.ocr.pedsf.Main;
import com.ocr.pedsf.model.GameProperties;
import com.ocr.pedsf.model.actors.Actor;
import com.ocr.pedsf.model.actors.ActorFactory;
import com.ocr.pedsf.vue.Result;
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

   private GameProperties mp;
   private int nbCoup = 0;

   public Autobaston() {
      if(Main.getMp()!=null) {
         this.mp = Main.getMp();
      }
   }

   @Override
   public void run() {
      log.traceEntry();
      boolean trouve;

      log.info("MASTERMIND : Mode AutoBaston\n");

      // initialisation des protagonistes
      Actor chapi = ActorFactory.get(ActorFactory.ACTOR_ROBOT,mp.getRobot1Name(),mp);
      Actor chapo = ActorFactory.get(ActorFactory.ACTOR_ROBOT,mp.getRobot2Name(),mp);

      if (mp.isDebugMode()) {
         log.info("(Combinaison secrète {} : {} )\n", chapi.getNom(),chapi.getNs().getNombre());
         log.info("(Combinaison secrète {} : {})\n", chapo.getNom(),chapo.getNs().getNombre());
      }

      do {
         // Chapi attaque Chapo
         trouve = chapi.attack(chapo);

         if(trouve) {
            Result.display(chapi.getNom(), chapo.getNom(), nbCoup, chapo.getNs().getNombre());
         } else {
            // au tour de Chapo
            trouve = chapo.attack(chapi);
            if(trouve)
               Result.display(chapo.getNom(), chapi.getNom(), nbCoup, chapi.getNs().getNombre());
         }

         nbCoup++;
      } while (!trouve);

      log.traceExit();
   }
}
