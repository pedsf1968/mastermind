package com.ocr.pedsf.vue;

import com.ocr.pedsf.utils.GestionSaisie;

public abstract class DemandeProposition {

   /**
    * demandeProposition : affichage de la demande de saisie d'un code
    *
    * @param digit taille du code à trouver
    * @return code saisi par l'utilisateur
    */
   public static String get(int digit){
      if(digit == 1) {
         System.out.print("Saisissez un nombre à un chiffre : ");
      } else {
         System.out.print("Saisissez un nombre à " + digit + " chiffres : ");
      }

      return GestionSaisie.demandeProposition(digit);
   }
}
