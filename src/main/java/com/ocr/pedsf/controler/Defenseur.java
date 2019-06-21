package com.ocr.pedsf.controler;

import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.NombreSecret;
import com.ocr.pedsf.vue.DemandeProposition;
import com.ocr.pedsf.vue.DemandeReponse;
import com.ocr.pedsf.vue.Resultat;

public class Defenseur {
   private MastermindProperties mp;
   private boolean trouve;
   private int nbCoup;
   private String reponse;

   public Defenseur(MastermindProperties mp){
      this.mp = mp;
      this.trouve = false;
      this.nbCoup = 0;
      this.reponse = "";
   }

   public void run(){
      System.out.println("\nMASTERMIND : Mode defenseur");

      // saisie du code de départ par l'utilisateur
      NombreSecret nsu = new NombreSecret(DemandeProposition.get(mp.getLongueur()));

      IA ia = new IA(mp.getLongueur());

      do {
         System.out.println("Votre code : " + nsu.getNombre() + " proposition de l'IA : " + ia.getNombreSecret());
         reponse = DemandeReponse.get(mp.getLongueur(),mp.isModeDeveloppeur());
         if(nsu.getNombre().equals(ia.getNombreSecret()))
            trouve = true;

         try {
            ia.proposition(reponse);
         } catch (TailleDifferenteException e) {
            e.printStackTrace();
         }

         this.nbCoup++;
      } while (!trouve && nbCoup<mp.getNbEssai());

      if (nbCoup>=mp.getNbEssai()) {
         Resultat.display("Utilisateur", "IA", nbCoup, nsu.getNombre());
      } else {
         Resultat.display("IA", "Utilisateur",nbCoup,nsu.getNombre());
      }

   }
}
