package com.ocr.pedsf.controler;

import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.NombreSecret;
import com.ocr.pedsf.vue.DemandeProposition;
import com.ocr.pedsf.vue.DemandeReponse;
import com.ocr.pedsf.vue.Resultat;


public class Duel {
   private MastermindProperties mp;
   private boolean trouve;
   private int nbCoup;
   private String proposition;
   private String reponse;

   public Duel(MastermindProperties mp){
      this.mp = mp;
      this.trouve = false;
      this.nbCoup = 1;
      this.proposition = "";
      this.reponse = "";
   }

   public void run(){
      System.out.println("\nMASTERMIND : Mode duel");

      // initialisation du nombre de l'ordinateur
      NombreSecret nso = new NombreSecret(mp.getLongueur());

      if(mp.isModeDeveloppeur())
         System.out.println("(Combinaison secrète IA : " + nso.getNombre()+")");

      // saisie du code de départ par l'utilisateur
      NombreSecret nsu = new NombreSecret(DemandeProposition.get(mp.getLongueur()));

      IA ia = new IA(mp.getLongueur());

      do {
         // l'utilisateur demande à l'ordinateur
         if(mp.isModeDeveloppeur()) {
            System.out.println("C'est à votre tour de trouver le code : " + nso.getNombre());
         } else {
            System.out.println("C'est à votre tour de trouver le code");
         }
         proposition = DemandeProposition.get(mp.getLongueur());
         try {
            System.out.println(nso.test(proposition));
         } catch (TailleDifferenteException e) {
            e.printStackTrace();
         }

         if(proposition.equals(nso.getNombre())) {
            trouve = true;
            Resultat.display("Utilisateur", "IA",nbCoup,nso.getNombre());
         } else {
            // si la réponse n'est pas trouvée c'est au tour de l'ordinateur
            System.out.println("C'est à l'IA de trouver le code : " + nsu.getNombre());
            try {
               if (!reponse.equals("")) ia.proposition(reponse);
            } catch (TailleDifferenteException e) {
               e.printStackTrace();
            }

            System.out.println("Votre code : " + nsu.getNombre() +" proposition de l'IA : " + ia.getNombreSecret());
            reponse = DemandeReponse.get(mp.getLongueur(), mp.isModeDeveloppeur());
            nbCoup++;

            if(nsu.getNombre().equals(ia.getNombreSecret())) {
               trouve = true;
               Resultat.display( "IA","Utilisateur",nbCoup,nso.getNombre());
            }
         }
      } while (!trouve);
   }
}
