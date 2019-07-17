package com.ocr.pedsf.vue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * DemandeReponse : class d'affichage pour demander une réponse de la proposition de l'adversaire
 *               on appel la class par la méthode get() qui se sert
 *               de display() pour afficher la question
 *               et de ask() pour lire les entrées clavier
 *               et retourne une réponse valide
 *
 * @author pedsf
 * @version 1.0
 */
public class DemandeReponse {
   private static final Logger log = LogManager.getLogger(DemandeReponse.class.getName());

   /**
    * get : méthode générale pour demander une réponse à la proposition de l'adversaire
    *       elle pause la question avec display()
    *       et demande à l'utilisateur avec ask() jusqu'à ce que la réponse soit correcte
    *
    * @param digit taille du code à trouver
    * @return réponse saisie par l'utilisateur
    */
   public static String get(int digit, boolean isDebugMode){
      log.traceEntry();

      do {
         if(isDebugMode) display();
         try {
            return log.traceExit(ask(digit));
         } catch (InputMismatchException e) {
            log.error("Mauvaise saisie !", e);
            System.out.println("Indiquez pour chaque chiffre de la combinaison proposée si" +
                  " le chiffre de sa combinaison est :\n" +
                  "plus grand par un (+), plus petit par un (-) identique par un (=)\n");
         }

      } while(true);
   }

   /**
    * display : méthode d'affichage de la question
    */
   static void display(){
      System.out.println("Indiquez pour chaque chiffre si le résultat est + grand - petit = égal.");
   }

   /**
    * ask : méthode de lecture sur l'entrée standard de la réponse de l'utilisateur
    *
    * @param digit taille du code à trouver
    * @return String code de +-=
    * @throws InputMismatchException si ce n'est pas une correspondance de +-=
    */
   static String ask(int digit) throws InputMismatchException{
      Scanner sc = new Scanner(System.in);
      String pattern = "[-+=]{" + digit + "}";

      return log.traceExit(sc.next(pattern));
   }
}
