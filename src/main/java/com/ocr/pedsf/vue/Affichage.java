package com.ocr.pedsf.vue;

import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.utils.GestionSaisie;

/**
 * Affichage : class pour gérer les vues de l'application Mastermind
 */
public class Affichage {
   private MastermindProperties mp;
   private GestionSaisie gs = new GestionSaisie();

   public Affichage(MastermindProperties mp){
      this.mp = mp;
   }

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
      System.out.println("4 - AutoBaston");
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
      System.out.println("Choisissez le nombre de digit entre 1 et " + max);

      return gs.demandeChoix(1,max);
   }

   /**
    * demandeProposition : affichage de la demande de saisie d'un code
    *
    * @param digit taille du code à trouver
    * @return code saisi par l'utilisateur
    */
   public String demandeProposition(int digit){
      if(digit == 1) {
         System.out.print("Saisissez un nombre à un chiffre : ");
      } else {
         System.out.print("Saisissez un nombre à " + digit + " chiffres : ");
      }

      return gs.demandeProposition(digit);
   }

   /**
    * demandeReponse : affichage de la demande de réponse de l'utilisateur à IA
    *
    * @param digit taille du code à trouver
    * @return réponse saisie par l'utilisateur
    */
   public String demandeReponse(int digit){

      if(mp.isModeDeveloppeur()) {
         System.out.println("Indiquez pour chaque chiffre de la combinaison proposée si" +
               " le chiffre de sa combinaison est :");
         System.out.println("plus grand par un (+)");
         System.out.println("plus petit par un (-)");
         System.out.println("plus identique par un (=)");
      } else {
         System.out.println("Indiquez votre réponse de " + digit + " signes");
      }

      return gs.demandeReponse(digit);
   }

   /**
    * resultat : affichage du résultat de la partie
    * @param gagnant nom du gagnant
    * @param perdant nom du perdant
    * @param coup nombre de coups
    * @param code code recherché
    */
   public void resultat(String gagnant, String perdant, int coup, String code){
      System.out.println("\nMASTERMIND");
      System.out.print(gagnant + " a gagné contre " + perdant + " en " + coup);
      if(coup==1) {
         System.out.println(" coup !");
      } else {
         System.out.println(" coups !");
      }

      System.out.println("Le code à trouver était : " + code);
   }
}
