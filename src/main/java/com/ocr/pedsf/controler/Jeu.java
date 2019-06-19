package com.ocr.pedsf.controler;

import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.NombreSecret;
import com.ocr.pedsf.vue.Affichage;

public class Jeu {
   private MastermindProperties mp = null;
   private Affichage affichage = new Affichage();

   public  Jeu(MastermindProperties mp){
      this.mp = mp;
     demarrage();
   }

   protected void demarrage(){
      int mode = affichage.choixDuMode();

      switch(mode){
         case 1 :
            challenger();
            break;
         case 2 :
            defenseur();
            break;
         case 3 :
            duel();
            break;
         case 0 :
            mp.setLongueur(affichage.choixNombreDigit(mp.getMaxDigit()));
            demarrage();
            break;
         default:
            System.out.println("\nMerci d'avoir jouer à MASTERMIND");
            break;
      }
   }


   private void challenger(){
      System.out.println("\nMASTERMIND : Mode challenger");
      boolean trouve = false;
      int nbCoup = 0;
      String proposition = "";
      NombreSecret ns = new NombreSecret(mp.getLongueur());

      if(mp.isModeDeveloppeur())
         System.out.println("(Combinaison secrète : " + ns.getNombre()+")");

      do {
         proposition = affichage.demandeProposition(mp.getLongueur());
         nbCoup++;

         try {
            System.out.println(ns.test(proposition));
            if(proposition.equals(ns.getNombre()))
               trouve = true;

         } catch (TailleDifferenteException e) {
            e.printStackTrace();
         }
      } while (!trouve && nbCoup<mp.getNbEssai());

      if (nbCoup<mp.getNbEssai()) {
         affichage.gagne(nbCoup);
      } else {
         affichage.perdu(ns.getNombre());
      }

      demarrage();
   }

   private void defenseur(){
      boolean trouve = false;
      int nbCoup = 0;
      String reponse = "";
      // saisie du code de départ par l'utilisateur
      NombreSecret nsu = new NombreSecret(affichage.demandeProposition(mp.getLongueur()));

      System.out.println("\nMASTERMIND : Mode defenseur");

      IA ia = new IA(mp.getLongueur());

      do {
         if (!reponse.equals(""))
            ia.proposition(reponse);

         System.out.println("Vous : " + nsu.getNombre());
         System.out.println("IA : " + ia.getNombreSecret());
         reponse = affichage.demandeReponse(mp.getLongueur());
         nbCoup++;

         if(nsu.getNombre()==ia.getNombreSecret())
            trouve = true;

      } while (!trouve && nbCoup<mp.getNbEssai());

      if (nbCoup<mp.getNbEssai()) {
         affichage.perdu(nsu.getNombre());
      } else {
         affichage.gagne(nbCoup);
      }

      demarrage();
   }

   private void duel(){
      System.out.println("\nMASTERMIND : Mode duel");
      demarrage();
   }
}
