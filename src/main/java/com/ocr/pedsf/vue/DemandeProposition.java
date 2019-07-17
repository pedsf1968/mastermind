package com.ocr.pedsf.vue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * DemandeProposition : class d'affichage pour demander une proposition de nombre secret au joueur
 *               on appel la class par la méthode get() qui se sert
 *               de display() pour afficher la question
 *               et de ask() pour lire les entrées clavier
 *               et retourne une réponse valide
 *
 * @author pedsf
 * @version 1.0
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
   public static String get(int digit, boolean isDebugMode){
      log.traceEntry();

      do {
         if(isDebugMode) display(digit);
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
