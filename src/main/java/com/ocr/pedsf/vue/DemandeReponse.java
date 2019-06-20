package com.ocr.pedsf.vue;

import com.ocr.pedsf.utils.GestionSaisie;

public abstract class DemandeReponse {


   /**
    * demandeReponse : affichage de la demande de réponse de l'utilisateur à IA
    *
    * @param digit taille du code à trouver
    * @return réponse saisie par l'utilisateur
    */
   public static String get(int digit, boolean isDebug){

      if(isDebug) {
         System.out.println("Indiquez pour chaque chiffre de la combinaison proposée si" +
               " le chiffre de sa combinaison est :");
         System.out.println("plus grand par un (+)");
         System.out.println("plus petit par un (-)");
         System.out.println("plus identique par un (=)");
      } else {
         System.out.println("Indiquez votre réponse de " + digit + " signes");
      }

      return GestionSaisie.demandeReponse(digit);
   }
}
