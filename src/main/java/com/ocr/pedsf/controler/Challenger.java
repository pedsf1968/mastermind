package com.ocr.pedsf.controler;

import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.NombreSecret;
import com.ocr.pedsf.vue.DemandeProposition;
import com.ocr.pedsf.vue.Resultat;

public class Challenger {
   private MastermindProperties mp;
   private boolean trouve;
   private int nbCoup;
   private String proposition;


   public Challenger(MastermindProperties mp){
      this.mp = mp;
      this.trouve = false;
      this.nbCoup = 1;
      this.proposition = "";
   }

   public void run() {
      System.out.println("\nMASTERMIND : Mode challenger");

      // initialisation du nombre de l'ordinateur
      NombreSecret nso = new NombreSecret(mp.getLongueur());

      if (mp.isModeDeveloppeur())
         System.out.println("(Combinaison secrète : " + nso.getNombre() + ")");

      do {
         proposition = DemandeProposition.get(mp.getLongueur());

         try {
            System.out.println(nso.test(proposition));
            if (proposition.equals(nso.getNombre()))
               trouve = true;

         } catch (TailleDifferenteException e) {
            e.printStackTrace();
         }
      } while (!trouve && ++nbCoup < mp.getNbEssai());

      if (nbCoup < mp.getNbEssai()) {
         Resultat.display("Utilisateur", "IA", nbCoup, nso.getNombre());
      } else {
         Resultat.display("IA", "Utilisateur", nbCoup, nso.getNombre());
      }
   }
}
