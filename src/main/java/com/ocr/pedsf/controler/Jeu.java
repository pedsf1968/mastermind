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

   /**
    * demarrage : méthode de lancement de l'accueil pour choisir le mode de jeu ou la taille du code
    */
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


   /**
    * challenger : méthode de gestion du mode challenger où l'utilisateur doit trouver le code de l'ordinateur
    */
   private void challenger(){
      System.out.println("\nMASTERMIND : Mode challenger");
      boolean trouve = false;
      int nbCoup = 0;
      String proposition = "";
      // initialisation du nombre de l'ordinateur
      NombreSecret nso = new NombreSecret(mp.getLongueur());

      if(mp.isModeDeveloppeur())
         System.out.println("(Combinaison secrète : " + nso.getNombre()+")");

      do {
         proposition = affichage.demandeProposition(mp.getLongueur());
         nbCoup++;

         try {
            System.out.println(nso.test(proposition));
            if(proposition.equals(nso.getNombre()))
               trouve = true;

         } catch (TailleDifferenteException e) {
            e.printStackTrace();
         }
      } while (!trouve && nbCoup<mp.getNbEssai());

      if (nbCoup<mp.getNbEssai()) {
         affichage.gagne(nbCoup);
      } else {
         affichage.perdu(nso.getNombre());
      }

      demarrage();
   }

   /**
    * defenseur : méthode de gestion du mode defenseur où l'utilisateur rentre un code et l'ordinateur doit trouver la solution
    */
   private void defenseur(){
      System.out.println("\nMASTERMIND : Mode defenseur");
      boolean trouve = false;
      int nbCoup = 0;
      String reponse = "";
      // saisie du code de départ par l'utilisateur
      NombreSecret nsu = new NombreSecret(affichage.demandeProposition(mp.getLongueur()));

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

   /**
    * duel : méthode de gestion du mode duel où l'utilisateur et l'ordinateur doivent chacun trouver le code de l'autre
    */
   private void duel(){
      System.out.println("\nMASTERMIND : Mode duel");
      boolean trouve = false;
      int nbCoup = 0;
      String reponse = "";
      String proposition = "";
      // initialisation du nombre de l'ordinateur
      NombreSecret nso = new NombreSecret(mp.getLongueur());

      if(mp.isModeDeveloppeur())
         System.out.println("(Combinaison secrète : " + nso.getNombre()+")");

      // saisie du code de départ par l'utilisateur
      NombreSecret nsu = new NombreSecret(affichage.demandeProposition(mp.getLongueur()));

      IA ia = new IA(mp.getLongueur());

      do {
         // l'utilisateur demande à l'ordinateur
         System.out.println("C'est à votre tour de trouver le code");
         proposition = affichage.demandeProposition(mp.getLongueur());
         try {
            System.out.println(nso.test(proposition));
         } catch (TailleDifferenteException e) {
            e.printStackTrace();
         }

         if(proposition.equals(nso.getNombre())) {
            trouve = true;
            affichage.gagne(nbCoup);
         } else {
            // si la réponse n'est pas trouvée c'est au tour de l'ordinateur
            System.out.println("C'est à l'ordinateur de trouver le code");
           if (!reponse.equals(""))
               ia.proposition(reponse);

            System.out.println("Vous : " + nsu.getNombre());
            System.out.println("IA : " + ia.getNombreSecret());
            reponse = affichage.demandeReponse(mp.getLongueur());
            nbCoup++;

            if(nsu.getNombre()==ia.getNombreSecret()) {
               trouve = true;
               affichage.perdu(nsu.getNombre());
            }
         }
      } while (!trouve);

      demarrage();
   }
}
