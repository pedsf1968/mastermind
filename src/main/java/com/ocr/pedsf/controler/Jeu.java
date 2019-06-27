package com.ocr.pedsf.controler;

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
    * run : méthode de lancement de l'accueil pour choisir le mode de jeu ou la taille du code
    */
   protected void run(){
      log.traceEntry();
      int choixMode = ChoixDuMode.get();

      switch(choixMode){
         case 0 :
            // spécification du nombre de digits du code
            mp.setLongueur(ChoixNombreDigit.get(mp.getMaxDigit()));
            if(mp.isModeDeveloppeur()) System.out.println("Nouveau nombre de digit : " + mp.getLongueur());
            run();
            break;
         case 1 :
            // lancement du mode Challenger
            mode = new Challenger(mp);
            mode.run();
            run();
            break;
         case 2 :
            //  lancement du mode défenseur
            mode = new Defenseur(mp);
            mode.run();
            run();
            break;
         case 3 :
            // lancement du mode duel
            mode = new Duel(mp);
            mode.run();
            run();
            break;
         case 4:
            // lancement du mode AutoBaston
            mode = new AutoBaston(mp);
            mode.run();
            run();
            break;
         default:
            // sortie du jeu
            System.out.println("\nMerci d'avoir jouer à MASTERMIND");
            log.debug("Sortie du jeu");
            break;
      }
   }
}
