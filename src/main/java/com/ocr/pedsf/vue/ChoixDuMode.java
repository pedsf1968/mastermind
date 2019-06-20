package com.ocr.pedsf.vue;

import com.ocr.pedsf.utils.GestionSaisie;

public abstract class ChoixDuMode {

   public static int getMode() {
      System.out.println("\nMASTERMIND");
      System.out.println("\nChoisissez le mode de jeu :");
      System.out.println("0 - Choix du nombre de digit");
      System.out.println("1 - Challenger");
      System.out.println("2 - Défenseur");
      System.out.println("3 - Duel");
      System.out.println("4 - AutoBaston");
      System.out.println("\nSaisissez un nombre plus grand pour quitter");

      return GestionSaisie.demandeChoix(0);
   }

}
