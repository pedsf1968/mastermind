package com.ocr.pedsf.controler;

import com.ocr.pedsf.controler.modes.AutoBaston;
import com.ocr.pedsf.controler.modes.Challenger;
import com.ocr.pedsf.controler.modes.Defenseur;
import com.ocr.pedsf.controler.modes.Duel;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.vue.ChoixDuMode;
import com.ocr.pedsf.vue.ChoixNombreDigit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Jeu class contrôleur pour le déroulement du Jeu
 *
 * @author pedsf
 */
public class Jeu {
   private static final Logger log = LogManager.getLogger(Jeu.class);

   private MastermindProperties mp = null;
   private Mode mode = null;

   public  Jeu(MastermindProperties mp){
      this.mp = mp;
     run();
   }

   /**
    * run : méthode de lancement de l'accueil pour choisir le modes de jeu ou la taille du code
    */
   protected void run(){
      log.traceEntry();
      int choixMode = ChoixDuMode.get();

      switch(choixMode){
         case 0 :
            // spécification du nombre de digits du code
            log.trace("Changement du nombre de digit");
            mp.setLongueur(ChoixNombreDigit.get(mp.getMaxDigit()));
            run();
            break;
         case 1 :
            // lancement du modes Challenger
            log.trace("Lancement du modes Challenger");
            mode = new Challenger(mp);
            mode.run();
            run();
            break;
         case 2 :
            //  lancement du modes défenseur
            log.trace("Lancement du modes Défenseur");
            mode = new Defenseur(mp);
            mode.run();
            run();
            break;
         case 3 :
            // lancement du modes duel
            log.trace("Lancement du modes Duel");
            mode = new Duel(mp);
            mode.run();
            run();
            break;
         case 4:
            // lancement du modes AutoBaston
            log.trace("Lancement du modes AutoBaston");
            mode = new AutoBaston(mp);
            mode.run();
            run();
            break;
         default:
            // sortie du jeu
            log.trace("Sortie du jeu");
            System.out.println("\nMerci d'avoir jouer à MASTERMIND");
            break;
      }
   }
}
