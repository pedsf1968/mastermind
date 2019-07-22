package com.ocr.pedsf.vue;

import com.ocr.pedsf.exceptions.BornageException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ChoixDuMode : class d'affichage pour le choix du modes de jeu
 *               on appel la class par la méthode get() qui se sert
 *               de display() pour afficher la question
 *               et de ask() pour lire les entrées clavier
 *               et retourne une réponse valide
 *
 * @author pedsf
 * @version 1.0
 */
public class ChoixDuMode {
   private static final Logger log = LogManager.getLogger(ChoixDuMode.class.getName());

   private ChoixDuMode() {
      throw new UnsupportedOperationException();
   }

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
      log.info("MASTERMIND\n");
      log.info("Choisissez le modes de jeu :");
      log.info("0 - Choix du nombre de digit");
      log.info("1 - Challenger");
      log.info("2 - Défenseur");
      log.info("3 - Duel");
      log.info("4 - AutoBaston\n");
      log.info("Saisissez un nombre plus grand pour quitter.\n");
   }

   /**
    * ask : méthode de lecture sur l'entrée standard de la réponse de l'utilisateur
    *
    * @return int entier positif
    * @throws BornageException si la valeur est négative
    * @throws InputMismatchException si ce n'est pas un nombre
    */
   static int ask() throws BornageException {
      Scanner sc = new Scanner(System.in);
      int response = sc.nextInt();

      if(response<0) throw new BornageException();

      return log.traceExit(response);
   }

}
