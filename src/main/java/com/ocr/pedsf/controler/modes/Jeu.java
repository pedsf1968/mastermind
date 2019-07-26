package com.ocr.pedsf.controler.modes;

import com.ocr.pedsf.Main;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.vue.AskGameMode;
import com.ocr.pedsf.vue.AskCodeLength;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Jeu : class  implémentant Mode qui sert de contrôleur pour le déroulement du Jeu
 *
 * @author pedsf
 * @version 1.0
 */
public class Jeu implements Mode{
   private static final Logger log = LogManager.getLogger(Jeu.class);
   private MastermindProperties mp;

   public  Jeu(){
      if(Main.getMp()!=null)
         this.mp = Main.getMp();
     run();
   }

   /**
    * run : méthode de lancement de l'accueil pour choisir le modes de jeu ou la taille du code
    */
   public void run(){
      log.traceEntry();
      int choixMode = AskGameMode.get();
      Mode mode;

      switch(choixMode){
         case 0 :
            // spécification du nombre de digits du code
            log.trace("Changement du nombre de digit");
            mp.setLength(AskCodeLength.get(mp.getMaxLength()));
            run();
            break;
         case 1 :
            // lancement du modes Challenger
            log.trace("Lancement du mode Challenger");
            mode = new Challenger();
            mode.run();
            run();
            break;
         case 2 :
            //  lancement du modes défenseur
            log.trace("Lancement du mode Défenseur");
            mode = new Defender();
            mode.run();
            run();
            break;
         case 3 :
            // lancement du modes duel
            log.trace("Lancement du mode Duel");
            mode = new Duel();
            mode.run();
            run();
            break;
         case 4:
            // lancement du modes AutoBaston
            log.trace("Lancement du modes AutoBaston");
            mode = new Autobaston();
            mode.run();
            run();
            break;
         default:
            // sortie du jeu
            log.trace("Sortie du jeu");
            log.info("Merci d'avoir joué à MASTERMIND\n");
            break;
      }
   }
}
