package com.ocr.pedsf.controler.modes;

import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.vue.ChoixDuMode;
import com.ocr.pedsf.vue.ChoixNombreDigit;
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

   public  Jeu(MastermindProperties mp){
      this.mp = mp;
     run();
   }

   /**
    * run : méthode de lancement de l'accueil pour choisir le modes de jeu ou la taille du code
    */
   public void run(){
      log.traceEntry();
      int choixMode = ChoixDuMode.get();
      Mode mode;

      switch(choixMode){
         case 0 :
            // spécification du nombre de digits du code
            log.trace("Changement du nombre de digit");
            mp.setLongueur(ChoixNombreDigit.get(mp.getMaxDigit()));
            run();
            break;
         case 1 :
            // lancement du modes Challenger
            log.trace("Lancement du mode Challenger");
            mode = new Challenger(mp);
            mode.run();
            run();
            break;
         case 2 :
            //  lancement du modes défenseur
            log.trace("Lancement du mode Défenseur");
            mode = new Defenseur(mp);
            mode.run();
            run();
            break;
         case 3 :
            // lancement du modes duel
            log.trace("Lancement du mode Duel");
            mode = new Duel(mp);
            mode.run();
            run();
            break;
         case 4:
            // lancement du modes AutoBaston
            log.trace("Lancement du modes AutoBaston");
            mode = new Autobaston(mp);
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
