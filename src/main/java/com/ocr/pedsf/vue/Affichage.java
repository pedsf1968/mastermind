package com.ocr.pedsf.vue;

import com.ocr.pedsf.utils.GestionSaisie;

/**
 * Affichage : class pour gérer les vues de l'application Mastermind
 */
public class Affichage {
   private GestionSaisie gs = new GestionSaisie();

   /**
    * choixDuMode : affichage de la demande du mode de jeu
    *
    * @return mode de jeu souhaité
    */
   public int choixDuMode(){

      System.out.println("\nMASTERMIND");
      System.out.println("\nChoisissez le mode de jeu :");
      System.out.println("0 - Choix du nombre de digit");
      System.out.println("1 - Challenger");
      System.out.println("2 - Défenseur");
      System.out.println("3 - Duel");
      System.out.println("\nSaisissez un nombre plus grand pour quitter");

      return gs.demandeChoix(0);
   }

   /**
    * choixNombreDigit : affichage de la demande de changement du nombre de digit pour le code
    *
    * @param max nombre de digit maximum que l'utilisateur peut spécifier
    * @return taille du code souhaité
    */
   public int choixNombreDigit(int max){

      System.out.println("\nMASTERMIND");
      System.out.println("Choisissez le nombre de digit entre 1 et " + max);

      return gs.demandeChoix(1,max);
   }

   /**
    * demandeProposition : affichage de la demande de saisie d'un code
    *
    * @param longueur taille du code à trouver
    * @return code saisi par l'utilisateur
    */
   public String demandeProposition(int longueur){
      System.out.println("\nMASTERMIND");
      System.out.println("Saisissez un nombre de "+ longueur + " digits");

      return gs.demandeProposition(longueur);
   }

   /**
    * gagne : affichage de l'écran final gagnant
    *
    * @param coup effectués par l'utilisateur
    */
   public void gagne(int coup){
      System.out.println("\nMASTERMIND");

      if(coup==1) {
         System.out.println("Vous avez gagné en 1 coup !");
      } else {
         System.out.println("Vous avez gagné en " + coup + " coups !");
      }

   }

   /**
    * perdu : affichage de l'écran final perdant
    *
    * @param code caché que l'utilisateur devais trouver
    */
   public void perdu(String code){
      System.out.println("\nMASTERMIND");
      System.out.println("Vous avez perdu !");
      System.out.println("Le code à trouver était : " + code);
   }
}
