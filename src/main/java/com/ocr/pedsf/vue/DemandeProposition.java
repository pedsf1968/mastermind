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

public abstract class DemandeProposition {
   private static final Logger log = LogManager.getLogger(DemandeProposition.class.getName());

   /**
    * demandeProposition : affichage de la demande de saisie d'un code
    *
    * @param digit taille du code à trouver
    * @return code saisi par l'utilisateur
    */
   public static String get(int digit){
      log.traceEntry();
      Scanner sc = new Scanner(System.in);
      String reponse = "";
      String pattern = "[0-9]{" + digit + "}";

      do {
         try {
            return log.traceExit(sc.next(pattern));
        } catch (InputMismatchException e) {
            sc.next();
            log.error("Mauvaise saisie !", e);
            System.out.println("\n Choisissez un nombre de "+ digit + " chiffre(s) !\n");
         }

      } while(true);

   }
}
