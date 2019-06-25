package com.ocr.pedsf.vue;

import com.ocr.pedsf.exceptions.MauvaiseReponseException;
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
   private static final Logger log = LogManager.getLogger(ChoixDuMode.class.getName());

   public static int get() {
      log.traceEntry();
      int reponse;

      do {
         try {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nMASTERMIND");
            System.out.println("\nChoisissez le mode de jeu :");
            System.out.println("0 - Choix du nombre de digit");
            System.out.println("1 - Challenger");
            System.out.println("2 - Défenseur");
            System.out.println("3 - Duel");
            System.out.println("4 - AutoBaston");
            System.out.println("\nSaisissez un nombre plus grand pour quitter");

            reponse = sc.nextInt();
            if(reponse>=0) {
               return log.traceExit(reponse);
            } else {
               throw new MauvaiseReponseException("\n Erreur de saisie! Choisissez un nombre positif.");
            }
         } catch (MauvaiseReponseException | InputMismatchException e) {
            log.error( e);
         }

      } while(true);

   }

}
