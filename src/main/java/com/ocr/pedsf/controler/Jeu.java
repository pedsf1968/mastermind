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
     demarrage();
   }

   /**
    * demarrage : méthode de lancement de l'accueil pour choisir le mode de jeu ou la taille du code
    */
   protected void demarrage(){
      log.debug("Affichage menu général");
      int mode = ChoixDuMode.get();

      switch(mode){
         case 0 :
            // spécification du nombre de digits du code
            mp.setLongueur(ChoixNombreDigit.get(mp.getMaxDigit()));
            if(mp.isModeDeveloppeur()) System.out.println("Nouveau nombre de digit : " + mp.getLongueur());
            demarrage();
            break;
         case 1 :
            // lancement du mode Challenger
            challenger = new Challenger(mp);
            challenger.run();
            demarrage();
            break;
         case 2 :
            //  lancement du mode défenseur
            defenseur = new Defenseur(mp);
            defenseur.run();
            demarrage();
            break;
         case 3 :
            // lancement du mode duel
            duel = new Duel(mp);
            duel.run();
            demarrage();
            break;
         case 4:
            // lancement du mode AutoBaston
            autoBaston = new AutoBaston(mp);
            autoBaston.run();
            demarrage();
            break;
         default:
            // sortie du jeu
            System.out.println("\nMerci d'avoir jouer à MASTERMIND");
            log.debug("Sortie du jeu");
            break;
      }
   }
}
