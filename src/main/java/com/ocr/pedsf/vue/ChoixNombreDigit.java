package com.ocr.pedsf.vue;

import com.ocr.pedsf.exceptions.BornageException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ChoixNombreDigit : Demande à l'utilisateur une nouvelle longueur du code
 *
 * @author pedsf
 */
public abstract class ChoixNombreDigit {
   private static final Logger log = LogManager.getLogger(ChoixNombreDigit.class.getName());

   /**
    * get : affichage de la demande de changement du nombre de digit pour le code
    *
    * @param max nombre de digit maximum que l'utilisateur peut spécifier
    * @return taille du code souhaité
    */
   public static int get(int max){
      log.traceEntry();


      do {
         try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Choisissez le nombre de digit entre 1 et " + max + " : ");
            int reponse = sc.nextInt();

            if (reponse == 1) {
               System.out.println("\nModification de la taille du nombre secret à "+reponse + " digit");
               return log.traceExit(reponse);
            } else if (reponse > 1 && reponse <= max) {
               System.out.println("\nModification de la taille du nombre secret à "+reponse + " digits");
               return log.traceExit(reponse);
            } else {
               throw new BornageException("\n Erreur de saisie! Choisissez un nombre de de digit entre 1 et " + max + " .\n");
            }

         } catch ( BornageException | InputMismatchException | IndexOutOfBoundsException  e) {
            log.error(e);
         }

      } while(true);
   }
}
