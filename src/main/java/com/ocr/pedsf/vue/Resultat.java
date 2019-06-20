package com.ocr.pedsf.vue;

public abstract class Resultat {

   /**
    * display : affichage du résultat de la partie
    * @param gagnant nom du gagnant
    * @param perdant nom du perdant
    * @param coup nombre de coups
    * @param code code recherché
    */
   public static void display(String gagnant, String perdant, int coup, String code){
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
