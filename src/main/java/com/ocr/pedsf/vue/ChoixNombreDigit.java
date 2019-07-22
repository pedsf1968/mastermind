package com.ocr.pedsf.vue;

import com.ocr.pedsf.exceptions.BornageException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ChoixNombreDigit : class d'affichage pour demander à l'utilisateur une nouvelle longueur du code
 *               on appel la class par la méthode get() qui se sert
 *               de display() pour afficher la question
 *               et de ask() pour lire les entrées clavier
 *               et retourne une réponse valide
 *
 * @author pedsf
 * @version 1.0
 */
public class ChoixNombreDigit {
   private static final Logger log = LogManager.getLogger(ChoixNombreDigit.class.getName());

   private ChoixNombreDigit() {
      throw new UnsupportedOperationException();
   }

   /**
    * get : méthode générale pour demander un nombre de digit pour le code
    *       elle pause la question avec display()
    *       et demande à l'utilisateur avec ask() jusqu'à ce que la réponse soit correcte
    *
    * @param max nombre de digit maximum que l'utilisateur peut spécifier
    * @return nouvelle taille du code souhaité
    */
   public static int get(int max){
      log.traceEntry();

      do {
         display(max);

         try {
            return log.traceExit(ask(max));
         } catch ( BornageException | InputMismatchException | IndexOutOfBoundsException  e) {
            log.error("\n Erreur de saisie! Choisissez un nombre de de digit entre 1 et {}.\n", max ,e);
         }

      } while(true);
   }

   /**
    * display : méthode d'affichage de la question
    *
    * @param max valeur maximale possible
    */
   static void display(int max){
      log.info("Choisissez le nombre de digit entre 1 et {} : ",max);
   }

   /**
    * ask : méthode de lecture sur l'entrée standard de la réponse de l'utilisateur
    *
    * @param max valeur maximale acceptée
    * @return int valeur saisie par l'utilisateur entre 1 et max
    * @throws BornageException si la valeur n'est pas entre 1 et max
    * @throws InputMismatchException si ce n'est pas un nombre
    */
   static int ask(int max) throws BornageException {
      Scanner sc = new Scanner(System.in);
      int response = sc.nextInt();

      if (response == 1) {
         log.info("Modification de la taille du nombre secret à {} digit.\n",response);
         return log.traceExit(response);
      } else if (response > 1 && response <= max) {
         log.info("Modification de la taille du nombre secret à {} digits.\n",response );
         return log.traceExit(response);
      } else {
         throw new BornageException("Erreur de saisie! Choisissez un nombre de de digit entre 1 et " + max + " .\n");
      }
   }
}
