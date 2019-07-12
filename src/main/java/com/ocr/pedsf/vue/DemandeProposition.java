package com.ocr.pedsf.vue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * DemandeProposition : demande une proposition au joueur
 *
 * @author pedsf
 */

public class DemandeProposition {
   private static final Logger log = LogManager.getLogger(DemandeProposition.class.getName());

   /**
    * get : méthode générale pour demander une proposition d'un code à l'utilisateur
    *       elle pause la question avec display()
    *       et demande à l'utilisateur avec ask() jusqu'à ce que la réponse soit correcte
    *
    * @param digit taille du code à trouver
    * @return code saisi par l'utilisateur
    */
   public static String get(int digit){
      log.traceEntry();

      do {
         display(digit);
         try {
            return log.traceExit(ask(digit));
        } catch (InputMismatchException e) {
            log.error("Mauvaise saisie !", e);
         }

      } while(true);
   }

   /**
    * display : méthode d'affichage de la question
    *
    * @param digit taille du code à trouver
    */
   static void display(int digit){
      System.out.println("Choisissez un nombre de "+ digit + " chiffre(s).");
   }

   /**
    * ask : méthode de lecture sur l'entrée standard de la réponse de l'utilisateur
    *
    * @param digit taille du code à trouver
    * @return int entier positif saisie par l'utilisateur
    * @throws InputMismatchException si ce n'est pas un nombre
    */
   static String ask(int digit) throws InputMismatchException{
      Scanner sc = new Scanner(System.in);
      String reponse = "";
      String pattern = "[0-9]{" + digit + "}";

      return log.traceExit(sc.next(pattern));
   }
}
