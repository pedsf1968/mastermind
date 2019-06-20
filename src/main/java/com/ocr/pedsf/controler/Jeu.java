package com.ocr.pedsf.controler;

import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.NombreSecret;
import com.ocr.pedsf.vue.Affichage;

public class Jeu {
   private MastermindProperties mp = null;
   private Affichage affichage = null;

   public  Jeu(MastermindProperties mp){
      this.mp = mp;
      this.affichage = new Affichage(mp);
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
         case 4:
            autobaston();
            break;
         case 0 :
            mp.setLongueur(affichage.choixNombreDigit(mp.getMaxDigit()));
            if(mp.isModeDeveloppeur()) System.out.println("Nouveau nombre de digit : " + mp.getLongueur());
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
      int nbCoup = 1;
      String proposition = "";
      // initialisation du nombre de l'ordinateur
      NombreSecret nso = new NombreSecret(mp.getLongueur());

      if(mp.isModeDeveloppeur())
         System.out.println("(Combinaison secrète : " + nso.getNombre()+")");

      do {
         proposition = affichage.demandeProposition(mp.getLongueur());


         try {
            System.out.println(nso.test(proposition));
            if(proposition.equals(nso.getNombre()))
               trouve = true;

         } catch (TailleDifferenteException e) {
            e.printStackTrace();
         }
      } while (!trouve && ++nbCoup<mp.getNbEssai());

      if (nbCoup<mp.getNbEssai()) {
         affichage.resultat("Utilisateur", "IA",nbCoup,nso.getNombre());
      } else {
         affichage.resultat("IA", "Utilisateur",nbCoup,nso.getNombre());
      }

      demarrage();
   }

   /**
    * defenseur : méthode de gestion du mode defenseur où l'utilisateur rentre un code et l'ordinateur doit trouver la solution
    */
   private void defenseur(){
      System.out.println("\nMASTERMIND : Mode defenseur");
      boolean trouve = false;
      int nbCoup = 1;
      String reponse = "";
      // saisie du code de départ par l'utilisateur
      NombreSecret nsu = new NombreSecret(affichage.demandeProposition(mp.getLongueur()));

      IA ia = new IA(mp.getLongueur());

      do {

            try {
               if (!reponse.equals("")) ia.proposition(reponse);
            } catch (TailleDifferenteException e) {
               e.printStackTrace();
            }

         System.out.println("Votre code : " + nsu.getNombre() + " proposition de l'IA : " + ia.getNombreSecret());
         reponse = affichage.demandeReponse(mp.getLongueur());

         if(nsu.getNombre().equals(ia.getNombreSecret()))
            trouve = true;

      } while (!trouve && ++nbCoup<mp.getNbEssai());

      if (nbCoup>=mp.getNbEssai()) {
         affichage.resultat("Utilisateur", "IA",nbCoup,nsu.getNombre());
      } else {
         affichage.resultat("IA", "Utilisateur",nbCoup,nsu.getNombre());
      }

      demarrage();
   }

   /**
    * duel : méthode de gestion du mode duel où l'utilisateur et l'ordinateur doivent chacun trouver le code de l'autre
    */
   private void duel(){
      System.out.println("\nMASTERMIND : Mode duel");
      boolean trouve = false;
      int nbCoup = 1;
      String reponse = "";
      String proposition = "";
      // initialisation du nombre de l'ordinateur
      NombreSecret nso = new NombreSecret(mp.getLongueur());

      if(mp.isModeDeveloppeur())
         System.out.println("(Combinaison secrète IA : " + nso.getNombre()+")");

      // saisie du code de départ par l'utilisateur
      NombreSecret nsu = new NombreSecret(affichage.demandeProposition(mp.getLongueur()));

      IA ia = new IA(mp.getLongueur());

      do {
         // l'utilisateur demande à l'ordinateur
         if(mp.isModeDeveloppeur()) {
            System.out.println("C'est à votre tour de trouver le code : " + nso.getNombre());
         } else {
            System.out.println("C'est à votre tour de trouver le code");
         }
         proposition = affichage.demandeProposition(mp.getLongueur());
         try {
            System.out.println(nso.test(proposition));
         } catch (TailleDifferenteException e) {
            e.printStackTrace();
         }

         if(proposition.equals(nso.getNombre())) {
            trouve = true;
            affichage.resultat("Utilisateur", "IA",nbCoup,nso.getNombre());
         } else {
            // si la réponse n'est pas trouvée c'est au tour de l'ordinateur
            System.out.println("C'est à l'IA de trouver le code : " + nsu.getNombre());
            try {
              if (!reponse.equals("")) ia.proposition(reponse);
            } catch (TailleDifferenteException e) {
                 e.printStackTrace();
            }

            System.out.println("Votre code : " + nsu.getNombre() +" proposition de l'IA : " + ia.getNombreSecret());
            reponse = affichage.demandeReponse(mp.getLongueur());
            nbCoup++;

            if(nsu.getNombre().equals(ia.getNombreSecret())) {
               trouve = true;
               affichage.resultat( "IA","Utilisateur",nbCoup,nso.getNombre());
            }
         }
      } while (!trouve);

      demarrage();
   }


   private void autobaston(){
      System.out.println("\nMASTERMIND : Mode autobaston");
      boolean trouve = false;
      int nbCoup = 1;
      String reponse1 = "";
      String reponse2 = "";
      String proposition1 = "";
      String proposition2 = "";
      // initialisation des nombres secrets des IA
      NombreSecret nso1 = new NombreSecret(mp.getLongueur());
      NombreSecret nso2 = new NombreSecret(mp.getLongueur());

      if(mp.isModeDeveloppeur())
         System.out.println("(Combinaison secrète 1: " + nso1.getNombre() + " secrète 2: " + nso2.getNombre() + ")");

      // on initialise les IA avec leur proposition aléatoire de départ
      IA ia1 = new IA(mp.getLongueur());
      IA ia2 = new IA(mp.getLongueur());

      do {
         // on récupère la proposition de IA1
         proposition1 = ia1.getNombreSecret();

         try {
            // IA1 fait une proposition à IA2
            reponse2 = nso2.test(proposition1);
            System.out.println("Code IA2 : " + nso2.getNombre() + " proposition IA1 : " + proposition1 + " réponse IA2 : " +reponse2);

            if(proposition1.equals(nso2.getNombre())) {
               trouve = true;
               affichage.resultat("IA1", "IA2",nbCoup,nso2.getNombre());
               // on sort de la boucle
               demarrage();
            }

            // recalcul suivant la réponse de IA2 pour le prochain tour
            ia1.proposition(reponse2);

            // si la réponse n'est pas trouvée c'est au tour de IA 2
            // on récupère la proposition de IA2
            proposition2 = ia2.getNombreSecret();

            // IA2 fait une proposition à IA1
            reponse1 = nso1.test(proposition2);
            System.out.println("Code IA1 : " + nso1.getNombre() + " proposition IA2 : " + proposition2 + " réponse IA1 : " +reponse1);

            if(proposition2.equals(nso1.getNombre())) {
               trouve = true;
               affichage.resultat("IA2", "IA1",nbCoup,nso1.getNombre());
               // on sort de la boucle
               demarrage();
            }

            // recalcul suivant la réponse de IA1
            ia2.proposition(reponse1);
         } catch (TailleDifferenteException e) {
            e.printStackTrace();
         }

         // on incrémente le conteur de tour
         nbCoup++;
      } while (!trouve);

      demarrage();
   }



}
