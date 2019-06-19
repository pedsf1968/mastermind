package com.ocr.pedsf.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * GestionSaisie : class pour faire des saisies sur l'entrée standard et contrôler la saisie
 */
public class GestionSaisie {
   Scanner sc = new Scanner(System.in);

   /**
    * demandeChoix : demande un entier entre les bornes min et max
    *
    * @param min borne minimale incluse
    * @param max borne maximale incluse
    * @return le nombre entier >= min et <= max
    */
   public int demandeChoix(int min, int max){
      int reponse = 0;
      boolean bonneReponse = false;

      do {
         try {
            reponse = sc.nextInt();
            bonneReponse = (reponse >= min && reponse <= max);
         } catch (IndexOutOfBoundsException e) {
            sc.next();
            bonneReponse = false;
         }

      } while(!bonneReponse);

      return reponse;
   }

   /**
    * demandeChoix : demande un entier à partir de la borne min
    * @param min borne minimale incluse
    * @return le nombre entier >= min
    */
   public int demandeChoix(int min){
      int reponse = 0;
      boolean bonneReponse = false;

      do {
         try {
            reponse = sc.nextInt();
            bonneReponse = (reponse >= min);
         } catch (IndexOutOfBoundsException e) {
            sc.next();
            bonneReponse = false;
         }

      } while(!bonneReponse);

      return reponse;
   }

   /**
    * demandeProposition : demande à l'utilisateur de saisir un code numérique
    *                      de la taille de digit
    *
    * @param digit taille du code
    * @return code saisi
    */
   public String demandeProposition(int digit){
      String reponse = "";
      boolean bonneReponse = false;
      String pattern = "[0-9]{" + digit + "}";

      do {
         try {
            reponse = sc.next(pattern);
            bonneReponse = true;

         } catch (InputMismatchException e) {
            sc.next();
            bonneReponse = false;
         }

      } while(!bonneReponse);

      return reponse;
   }

   /**
    * demandeReponse : demande à l'utilisateur la correspondance avec le code en +-=
    *
    * @param digit nombre de digit du code
    * @return la réponse sous forme de +-=
    */
   public String demandeReponse(int digit){
      String reponse = "";
      boolean bonneReponse = false;
      String pattern = "[-+=]{" + digit + "}";

      do {
         try {
            reponse = sc.next(pattern);
            bonneReponse = true;

         } catch (InputMismatchException e) {
            sc.next();
            bonneReponse = false;
         }

      } while(!bonneReponse);

      return reponse;
   }
}
