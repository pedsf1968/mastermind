package com.ocr.pedsf.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class PropertiesUtils {
   private static final Logger log = LogManager.getLogger(PropertiesUtils.class);
   private static final String MESSAGE_NO_VALUE = "no {} value in configuration file";

   /**
    * getBundleBoolean : méthode qui lit un booléen dans le bundel et retourne la valeur par défaut s'il n'y a pas de valeur
    *
    * @param bundle source that contain the properties
    * @param key nom du paramètre dans le fichier properties
    * @param defaultValue valeur d'initialisation par défaut
    * @return  valeur lue ou celle par défaut
    */
   public static boolean getBundleBoolean(ResourceBundle bundle, String key, boolean defaultValue){
      log.traceEntry();
      String value;

      try {
         value = bundle.getString(key);
         return log.traceExit((value.equals("")) ? defaultValue : Boolean.valueOf(value));
      } catch (MissingResourceException mrE) {
         log.error(key + MESSAGE_NO_VALUE + mrE);
         return log.traceExit(defaultValue);
      }
   }

   /**
    * getBundleInt : méthode qui lit un int dans le bundel et retourne la valeur par défaut s'il n'y a pas de valeur
    *
    * @param bundle source that contain the properties
    * @param key nom du paramètre dans le fichier properties
    * @param defaultValue valeur d'initialisation par défaut
    * @return  valeur lue ou celle par défaut
    */
   public static int getBundleInt(ResourceBundle bundle, String key, int defaultValue){
      log.traceEntry();
      String value;

      try {
         value = bundle.getString(key);
         return log.traceExit((value.equals("")) ? defaultValue : Integer.valueOf(value));
      } catch (MissingResourceException mrE) {
         log.error(key + MESSAGE_NO_VALUE + mrE);
         return log.traceExit(defaultValue);
      }
   }

   /**
    * getBundleString : méthode qui lit une String dans le bundel et retourne la valeur par défaut s'il n'y a pas de valeur
    *
    * @param bundle source that contain the properties
    * @param key nom du paramètre dans le fichier properties
    * @param defaultValue valeur d'initialisation par défaut
    * @return  valeur lue ou celle par défaut
    */
   public static String getBundleString(ResourceBundle bundle, String key, String defaultValue){
      log.traceEntry();
      String value;

      try {
         value = bundle.getString(key);
         return log.traceExit((value.equals("")) ? defaultValue : value);
      } catch (MissingResourceException mrE) {
         log.error(MESSAGE_NO_VALUE, key);
         return log.traceExit(defaultValue);
      }
   }

}
