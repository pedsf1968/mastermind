package com.ocr.pedsf.model;

import com.ocr.pedsf.exceptions.ParametreIncorrectException;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static java.lang.System.exit;


/**
 * MastermindProperties : class pour gérer les propriétés du jeux
 *                        elle récupère les paramètres de l'application
 *                        dans le fichier mastermind.properties
 *                        et dans la ligne de commande
 *
 * @author pedsf
 * @version 1.0
 */
public class MastermindProperties {
   private static final Logger log = LogManager.getLogger(MastermindProperties.class);
   private static final String MESSAGE_NO_VALUE = " absente du fichier de configuration ";

   // nom du fichier de configuration
   private static String constMastermindConfigurationFile = "mastermind";

   // constantes pour le nombre secret
   private static int constMastermindLongueur = 4;
   private static int constMastermindEssais = 10;
   private static int constMastermindMaxDigit = 10;
   private static boolean constMastermindDebugMode = false;

   // les noms des protagonistes par défaut
   private static String constMastermindNomUser = "Utilisateur";
   private static String constMastermindNomRobot1 = "Chapi";
   private static String constMastermindNomRobot2 = "Chapo";

   // Les tags par défaut pour passer des paramètres en arguments de la ligne de commande
   private static String constMastermindTagDebug = "-d";
   private static String constMastermindTagNomUser = "-u";
   private static String constMastermindTagMaxDigit = "-m";

   // variable pour lire les paramètres dans le fichier properties
   private ResourceBundle bundle;

   // variables pour le nombre secret
   private int longueur;         // longueur du code à trouver
   private int nbEssai;          // nombre max d'essai
   private int maxDigit;         // longueur max autorisé du code. peut être changé avec : -m nouvelle_valeur
   private boolean isDebugMode;  // mode développeur. peut être activé avec : -d

   // les noms des protagonistes
   private String nomUser;       // nom utilisateur. peut être changé avec : -u nouveau_nom
   private String nomRobot1;     // nom du premier robot qui sert dans tous les modes
   private String nomRobot2;     // nom du second robot pour le mode autobaston

   // les tags de la ligne de commande
   private final String tagDebug;      // tag pour activer le mode debug en ligne de commande
   private final String tagUser;       // tag pour changer le nom de l'utilisateur
   private final String tagMaxDigit;   // tag pour changet le nombre max de digit

   public MastermindProperties( String[] args){
      // initialisation de la resource bundle
      bundle = ResourceBundle.getBundle(constMastermindConfigurationFile);

      // lecture des paramètres dans le fichier
      this.longueur = getInt("mastermind.combinaison.chiffres", constMastermindLongueur);
      this.nbEssai = getInt("mastermind.combinaison.essais", constMastermindEssais);
      this.maxDigit = getInt("mastermind.combinaison.maxdigit", constMastermindMaxDigit);
      this.isDebugMode = getBoolean("mastermind.mode.developpeur", constMastermindDebugMode);
      this.nomUser = getString("mastermind.nom.user",constMastermindNomUser);
      this.nomRobot1 = getString("mastermind.nom.robot1", constMastermindNomRobot1);
      this.nomRobot2 = getString("mastermind.nom.robot2", constMastermindNomRobot2);
      this.tagDebug = getString("mastermind.tag.debug", constMastermindTagDebug);
      this.tagUser = getString("mastermind.tag.user", constMastermindTagNomUser);
      this.tagMaxDigit = getString("mastermind.tag.maxdigit", constMastermindTagMaxDigit);

      // on écrase avec les propriétées passées en ligne de commande
      List<String> paramList = new ArrayList<>(Arrays.asList(args));
      getArguments(paramList);
   }

   public void setLongueur(int longueur) {
      this.longueur = longueur;
   }

   public int getLongueur() {
      return longueur;
   }

   public int getNbEssai() {
      return nbEssai;
   }

   public int getMaxDigit() {
      return maxDigit;
   }

   public boolean isDebugMode() {
      return isDebugMode;
   }

   public String getNomUser() {
      return nomUser;
   }

   public String getNomRobot1() {
      return nomRobot1;
   }

   public String getNomRobot2() {
      return nomRobot2;
   }

   @Override
   public String toString() {
      return "MastermindProperties {" +
            "\nLongueur : " + longueur +
            "\nNombre d'essais : " + nbEssai +
            "\nTaille maximale : " + maxDigit +
            "\nMode développeur actif : " + isDebugMode +
            "\nNom utilisateur : '" + nomUser + '\'' +
            "\nNom robot 1 : '" + nomRobot1 + '\'' +
            "\nNom robot 2 : '" + nomRobot2 + '\'' +
            "\ntagDebug : '" + tagDebug + '\'' +
            "\ntagUser : '" + tagUser + '\'' +
            "\ntagMaxDigit : '" + tagMaxDigit + '\'' +
            '}';
   }

   /**
    * getArguments : méthode qui récupère les paramètres en ligne de commande
    *
    * @param paramList arguments de la ligne de commande
    */
   private void getArguments(List<String> paramList){
      log.traceEntry();
      int index = 0;

      // on récupère les paramètres en ligne de commande
      if (paramList.contains(this.tagDebug)){
         // c'est l'activation du mode développeur
         this.isDebugMode = true;
         paramList.remove(this.tagDebug);
         log.info("Mode debug activé !");
         log.trace("Mode debug activé !");
      }

      if( paramList.contains(this.tagUser)){
         // récupération du nom de l'utilisateur
         index = paramList.indexOf(this.tagUser) + 1;
         this.nomUser = paramList.get(index);
         paramList.remove(index);
         paramList.remove(index-1);
         log.info("User name : " + getNomUser());
         log.trace("User name : " + getNomUser());
      }

      if(paramList.contains(this.tagMaxDigit)) {
         // récupération de la taille maximale
         try {
            index = paramList.indexOf(this.tagMaxDigit) + 1;
            this.maxDigit = Integer.valueOf(paramList.get(index));
            paramList.remove(index);
            paramList.remove(index-1);
            log.info("MaxDigit : " + getMaxDigit());
            log.trace("MaxDigit : " + getMaxDigit());
         } catch (NumberFormatException nfE) {
            log.error("Mauvaise valeur de MaxDigit : " + paramList.get(index) + "\n" + nfE);
         }
      }

      // si la liste n'est pas vide il y a des paramètres incompris
      if(!paramList.isEmpty()
         &&( !StringUtils.equals(paramList.get(0),this.tagDebug)
         || !StringUtils.equals(paramList.get(0),this.tagUser)
         || !StringUtils.equals(paramList.get(0),this.tagMaxDigit))) {
         try {
            throw new ParametreIncorrectException();
         } catch (ParametreIncorrectException e) {
            // affichage de l'aide en ligne
            log.info("\nOption non reconnue : {}\n", paramList.get(0));
            log.info("mastermind [{}][{} nom][{} nombre]", this.tagDebug,this.tagUser,this.tagMaxDigit);
            log.info("{} : activate debug",this.tagDebug);
            log.info("{} nom : set user name",this.tagUser);
            log.info("{} nombre: set maxdigit\n",this.tagMaxDigit);
            //on supprime le paramètre défectueux et on rappel la méthode de lecture des paramètres
            paramList.remove(0);
            getArguments(paramList);
         }

      }

      log.traceExit();
   }

   /**
    * getBoolean : méthode qui lit un booléen dans le bundel et retourne la valeur par défaut s'il n'y a pas de valeur
    *
    * @param key nom du paramètre dans le fichier properties
    * @param defaultValue valeur d'initialisation par défaut
    * @return  valeur lue ou celle par défaut
    */
   private  boolean getBoolean(String key, boolean defaultValue){
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
    * getInt : méthode qui lit un int dans le bundel et retourne la valeur par défaut s'il n'y a pas de valeur
    *
    * @param key nom du paramètre dans le fichier properties
    * @param defaultValue valeur d'initialisation par défaut
    * @return  valeur lue ou celle par défaut
    */
   private int getInt(String key, int defaultValue){
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
    * getString : méthode qui lit une String dans le bundel et retourne la valeur par défaut s'il n'y a pas de valeur
    *
    * @param key nom du paramètre dans le fichier properties
    * @param defaultValue valeur d'initialisation par défaut
    * @return  valeur lue ou celle par défaut
    */
   private String getString(String key, String defaultValue){
      log.traceEntry();
      String value;

      try {
         value = bundle.getString(key);
         return log.traceExit((value.equals("")) ? defaultValue : value);
      } catch (MissingResourceException mrE) {
         log.error(key + MESSAGE_NO_VALUE + mrE);
         return log.traceExit(defaultValue);
      }
   }

}
