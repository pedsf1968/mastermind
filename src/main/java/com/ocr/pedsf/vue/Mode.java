package com.ocr.pedsf.vue;

import com.ocr.pedsf.exceptions.BornageException;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Mode {
   static final Logger log = LogManager.getLogger(ChoixDuMode.class);

   /**
    * display : méthode d'affichage de la question
    */
   protected static void display(String message){
      System.out.println(message);
   }

   /**
    * ask : méthode qui demande à l'utilisateur un entier entre les bornes min et max
    *
    * @param min borne min incluse
    * @param max borne max incluse
    * @return entier entre min et max
    * @throws BornageException si la saisie de l'utilisateur n'est pas dans les bornes
    */
   protected static int ask(int min, int max) throws BornageException {
      Scanner sc = new Scanner(System.in);
      int response = sc.nextInt();

      if(response<=min || response>=max) throw new BornageException();

      return log.traceExit(response);
   }

   /**
    * ask : méthode qui demande à l'utilisateur un entier supérieur ou égal à min
    *
    * @param min borne min incluse
    * @return entier égal ou plus grand que min
    * @throws BornageException si la saisie de l'utilisateur n'est pas dans les bornes
    */
   protected static int ask(int min) throws BornageException {
      Scanner sc = new Scanner(System.in);
      int response = sc.nextInt();

      if(response<min) throw new BornageException();

      return log.traceExit(response);
   }

   /**
    * ask : méthode qui demande à l'utilisateur une chaine respectant le pattern
    *
    * @param pattern description de la chaîne à saisir
    * @return retourne la chaîne vérifiant le pattern
    */
   protected static String ask( String pattern){
      Scanner sc = new Scanner(System.in);
      return log.traceExit(sc.next(pattern));
   }

   /**
    *
    * @param message
    * @param errorMessage
    * @param min
    * @param max
    * @return
    */
   public static int getInt(String message, String errorMessage, int min, int max) {
      log.traceEntry();

      do {
         display(message);
         try {
            return log.traceExit(ask(min,max));
         } catch (BornageException | InputMismatchException e) {
            log.error(errorMessage);
         }
      } while(true);
   }

   /**
    *
    * @param message
    * @return
    */
   public static int getIntPositif(String message, String errorMessage) {
      log.traceEntry();

      do {
         display(message);
         try {
            return log.traceExit(ask(0));
         } catch (BornageException | InputMismatchException e) {
            log.error(errorMessage);
         }
      } while(true);
   }

   /**
    *
    * @param message
    * @param errorMessage
    * @param digit
    * @param pattern
    * @param isDebugMode
    * @return
    */
   public static String getString(String message, String errorMessage, int digit, String pattern, boolean isDebugMode){
      log.traceEntry();

      do {
         if(isDebugMode) display(message);
         try {
            return log.traceExit(ask(pattern));
         } catch (InputMismatchException e) {
            log.error(errorMessage);
         }

      } while(true);
   }

}
