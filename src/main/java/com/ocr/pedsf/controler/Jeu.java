package com.ocr.pedsf.controler;

import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.vue.ChoixDuMode;
import com.ocr.pedsf.vue.ChoixNombreDigit;

public class Jeu {
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
      int mode = ChoixDuMode.getMode();

      switch(mode){
         case 1 :
            //challenger();
            challenger = new Challenger(mp);
            challenger.run();
            demarrage();
            break;
         case 2 :
            defenseur = new Defenseur(mp);
            defenseur.run();
            demarrage();
            break;
         case 3 :
            duel = new Duel(mp);
            duel.run();
            demarrage();
            break;
         case 4:
            autoBaston = new AutoBaston(mp);
            autoBaston.run();
            demarrage();
            break;
         case 0 :
            mp.setLongueur(ChoixNombreDigit.get(mp.getMaxDigit()));
            if(mp.isModeDeveloppeur()) System.out.println("Nouveau nombre de digit : " + mp.getLongueur());
            demarrage();
            break;
         default:
            System.out.println("\nMerci d'avoir jouer à MASTERMIND");
            break;
      }
   }
}
