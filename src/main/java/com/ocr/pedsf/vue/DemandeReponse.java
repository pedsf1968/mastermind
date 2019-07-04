package com.ocr.pedsf.vue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DemandeReponse {
   private static final Logger log = LogManager.getLogger(DemandeReponse.class.getName());

   /**
    * demandeReponse : affichage de la demande de réponse de l'utilisateur à IA
    *
    * @param digit taille du code à trouver
    * @return réponse saisie par l'utilisateur
    */
   public static String get(int digit, boolean isDebug){
      log.traceEntry();

      do {
         display();
         try {
            return log.traceExit(ask(digit));
         } catch (InputMismatchException e) {
            log.error("Mauvaise saisie !", e);
            System.out.println("Indiquez pour chaque chiffre de la combinaison proposée si" +
                  " le chiffre de sa combinaison est :\n" +
                  "plus grand par un (+), plus petit par un (-) identique par un (=)\n");
         }

      } while(true);
   }

   protected static void display(){
      System.out.println("Indiquez pour chaque chiffre si le résultat est + grand - petit = égal.");
   }

   protected static String ask(int digit){
      Scanner sc = new Scanner(System.in);
      String pattern = "[-+=]{" + digit + "}";

      return log.traceExit(sc.next(pattern));
   }
}
