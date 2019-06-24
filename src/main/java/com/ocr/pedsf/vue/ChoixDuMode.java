package com.ocr.pedsf.vue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ChoixDuMode : choix du mode de jeu
 *
 * @author pedsf
 */
public abstract class ChoixDuMode {
   private static final Logger log = LogManager.getLogger(ChoixDuMode.class);

   public static int get() {
      Scanner sc = new Scanner(System.in);


      do {
         try {
            int reponse;
            System.out.println("\nMASTERMIND");
            System.out.println("\nChoisissez le mode de jeu :");
            System.out.println("0 - Choix du nombre de digit");
            System.out.println("1 - Challenger");
            System.out.println("2 - Défenseur");
            System.out.println("3 - Duel");
            System.out.println("4 - AutoBaston");
            System.out.println("\nSaisissez un nombre plus grand pour quitter");

            reponse = sc.nextInt();
            log.debug("Choix du mode de jeu : "+ reponse);
            return reponse;
         } catch (InputMismatchException | IndexOutOfBoundsException e) {
            log.error("Mauvaise saisie !", e);
            sc.next();
            System.out.println("\n Choisissez un nombre positif !\n");
         }

      } while(true);

   }

}
