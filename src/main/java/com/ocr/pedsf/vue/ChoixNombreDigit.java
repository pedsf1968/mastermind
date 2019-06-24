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
   private static final Logger log = LogManager.getLogger(ChoixNombreDigit.class);

   /**
    * choixNombreDigit : affichage de la demande de changement du nombre de digit pour le code
    *
    * @param max nombre de digit maximum que l'utilisateur peut spécifier
    * @return taille du code souhaité
    */
   public static int get(int max){
      Scanner sc = new Scanner(System.in);

      do {
         try {
            System.out.print("Choisissez le nombre de digit entre 1 et " + max + " : ");
            int reponse = sc.nextInt();

            if (reponse == 1) {
               log.info("Modification de la longueur du code à 1 digit.");
               log.debug("Modification de la longueur du code à 1 digit");
               return reponse;
            } else if (reponse > 1 && reponse <= max) {
               log.info("Modification de la longueur du code à " + reponse + " digits.");
               log.debug("Modification de la longueur du code à " + reponse + " digits");
               return reponse;
            } else {
               throw new BornageException("Entrez un nombre entre 1 et " + max);
            }
         } catch (BornageException | InputMismatchException | IndexOutOfBoundsException  e) {
            sc.next();
            log.error("Mauvaise saisie !", e);
            System.out.println("\n Choisissez un nombre de de digit entre 1 et " + max + " !\n");
         }

      } while(true);
   }
}
