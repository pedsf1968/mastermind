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

   /**
    * get : méthode générale pour demander le choix du mode de jeu
    *       elle pause la question avec display()
    *       et demande à l'utilisateur avec ask() jusqu'à ce que la réponse soit correcte
    *
    * @return int réponse de l'utilisateur
    */
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

   /**
    * display : méthode d'affichage de la question
    */
   static void display(){
      System.out.println("\nMASTERMIND");
      System.out.println("\nChoisissez le modes de jeu :");
      System.out.println("0 - Choix du nombre de digit");
      System.out.println("1 - Challenger");
      System.out.println("2 - Défenseur");
      System.out.println("3 - Duel");
      System.out.println("4 - AutoBaston");
      System.out.println("\nSaisissez un nombre plus grand pour quitter");
   }

   /**
    * ask : méthode de lecture sur l'entrée standard de la réponse de l'utilisateur
    *
    * @return int entier positif
    * @throws BornageException si la valeur est négative
    * @throws InputMismatchException si ce n'est pas un nombre
    */
   static int ask() throws BornageException, InputMismatchException {
      Scanner sc = new Scanner(System.in);
      int response = sc.nextInt();

      if(response<0) throw new BornageException();

      return log.traceExit(response);
   }

}
