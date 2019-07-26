package com.ocr.pedsf.vue;

import com.ocr.pedsf.exceptions.BornageException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe implémentant les méthodes pour demander et controler la saisie de l'utilisateur pour les
 * différentes vues avec affichage de messages.
 */
public class Ask {
   private static Logger log = LogManager.getLogger(Ask.class);

   protected Ask() {
       throw new UnsupportedOperationException();
   }


   /**
    * display : méthode d'affichage de la question
    * @param message affiché par la méthode
    */
   protected static void display(String message){
      log.info(message);
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

      if(response<min || response>max) throw new BornageException();

      return response;
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

      return response;
   }

   /**
    * ask : méthode qui demande à l'utilisateur une chaine respectant le pattern
    *
    * @param pattern description de la chaîne à saisir
    * @return retourne la chaîne vérifiant le pattern
    */
   protected static String ask( String pattern){
      Scanner sc = new Scanner(System.in);
      return sc.next(pattern);
   }

   /**
    * getInt : méthode qui permet de demander un entier entre min et max avec
    *          un message de question et un d'erreur
    * @param message de la question
    * @param errorMessage de l'erreur
    * @param min borne minimale incluse
    * @param max borne maxmale incluse
    * @return un entier valide entre min et max
    */
   public static int getInt(String message, String errorMessage, int min, int max) {

      do {
         display(message);
         try {
            return ask(min,max);
         } catch (BornageException | InputMismatchException e) {
            log.error(errorMessage);
         }
      } while(true);
   }

   /**
    * getIntPositif : méthode qui permet de demander un entier positif avec
    *          un message de question et un d'erreur
    * @param message de la question
    * @param errorMessage de l'erreur
    * @return un entier positif valide
    */
   public static int getIntPositif(String message, String errorMessage) {

      do {
         display(message);
         try {
            return ask(0);
         } catch (BornageException | InputMismatchException e) {
            log.error(errorMessage);
         }
      } while(true);
   }

   /**
    * getString : méthode qui permet de demander une chaine de caractère vérifiant une pattern avec
    *             un message de question si isDebugMode est true et un d'erreur
    *
    * @param message de la question
    * @param errorMessage de l'erreur
    * @param pattern de la chaîne à respecter
    * @param isDebugMode active l'affichage du message
    * @return chaîne valide
    */
   public static String getString(String message, String errorMessage, String pattern, boolean isDebugMode){

      do {
         if(isDebugMode) display(message);
         try {
            return ask(pattern);
         } catch (InputMismatchException e) {
            log.error(errorMessage);
         }

      } while(true);
   }

}
