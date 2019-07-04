package com.ocr.pedsf.vue;

import com.ocr.pedsf.exceptions.BornageException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ChoixDuMode : choix du modes de jeu
 *
 * @author pedsf
 */
public class ChoixDuMode {
   private static final Logger log = LogManager.getLogger(ChoixDuMode.class.getName());

   public static int get() {
      log.traceEntry();

      do {
          display();
         try {
            return log.traceExit(ask());
         } catch (BornageException | InputMismatchException e) {
            log.error("\n Erreur de saisie! Choisissez un nombre positif.\n",e);
         }
      } while(true);
   }

   protected static void display(){
      System.out.println("\nMASTERMIND");
      System.out.println("\nChoisissez le modes de jeu :");
      System.out.println("0 - Choix du nombre de digit");
      System.out.println("1 - Challenger");
      System.out.println("2 - Défenseur");
      System.out.println("3 - Duel");
      System.out.println("4 - AutoBaston");
      System.out.println("\nSaisissez un nombre plus grand pour quitter");
   }

   protected static int ask() throws BornageException, InputMismatchException {
      Scanner sc = new Scanner(System.in);
      int response = sc.nextInt();

      if(response<0) throw new BornageException();

      return log.traceExit(response);
   }

}
