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
   private Challenger challenger = null;
   private Defenseur defenseur = null;
   private Duel duel = null;
   private AutoBaston autoBaston = null;

   public  Jeu(MastermindProperties mp){
      this.mp = mp;
     run();
   }

   /**
    * run : méthode de lancement de l'accueil pour choisir le mode de jeu ou la taille du code
    */
   protected void run(){
      log.traceEntry();
      int mode = ChoixDuMode.get();

      switch(mode){
         case 0 :
            // spécification du nombre de digits du code
            mp.setLongueur(ChoixNombreDigit.get(mp.getMaxDigit()));
            if(mp.isModeDeveloppeur()) System.out.println("Nouveau nombre de digit : " + mp.getLongueur());
            run();
            break;
         case 1 :
            // lancement du mode Challenger
            challenger = new Challenger(mp);
            challenger.run();
            run();
            break;
         case 2 :
            //  lancement du mode défenseur
            defenseur = new Defenseur(mp);
            defenseur.run();
            run();
            break;
         case 3 :
            // lancement du mode duel
            duel = new Duel(mp);
            duel.run();
            run();
            break;
         case 4:
            // lancement du mode AutoBaston
            autoBaston = new AutoBaston(mp);
            autoBaston.run();
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
