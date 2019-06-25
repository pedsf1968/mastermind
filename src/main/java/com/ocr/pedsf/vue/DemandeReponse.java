package com.ocr.pedsf.vue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class DemandeReponse {
   private static final Logger log = LogManager.getLogger(DemandeReponse.class.getName());

   /**
    * demandeReponse : affichage de la demande de réponse de l'utilisateur à IA
    *
    * @param digit taille du code à trouver
    * @return réponse saisie par l'utilisateur
    */
   public static String get(int digit, boolean isDebug){
      log.traceEntry();
      Scanner sc = new Scanner(System.in);
      String reponse = "";
      String pattern = "[-+=]{" + digit + "}";

      do {
         try {
            return log.traceExit(sc.next(pattern));
         } catch (InputMismatchException e) {
            sc.next();
            log.error("Mauvaise saisie !", e);
            System.out.println("Indiquez pour chaque chiffre de la combinaison proposée si" +
                  " le chiffre de sa combinaison est :\n" +
                  "plus grand par un (+), plus petit par un (-) identique par un (=)\n");
         }

      } while(true);
   }
}
