package com.ocr.pedsf.vue;

import com.ocr.pedsf.utils.GestionSaisie;

public abstract class ChoixNombreDigit {

   /**
    * choixNombreDigit : affichage de la demande de changement du nombre de digit pour le code
    *
    * @param max nombre de digit maximum que l'utilisateur peut spécifier
    * @return taille du code souhaité
    */
   public static int get(int max){
      System.out.println("Choisissez le nombre de digit entre 1 et " + max);

      return GestionSaisie.demandeChoix(1,max);
   }
}
