package com.ocr.pedsf.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import static java.lang.System.exit;

/**
 * MastermindProperties : class pour gérer les propriétés du jeux
 *
 * @author pedsf
 */
public class MastermindProperties {
   private static final Logger log = LogManager.getLogger(MastermindProperties.class);

   // emplacement du fichier de configuration
   private static String CONFIGURATION_FILE = "mastermind";
   private static ResourceBundle bundle;

   private static int MASTERMIND_LONGUEUR = 4;
   private static int MASTERMIND_ESSAIS = 10;
   private static int MASTERMIND_MAX_DIGIT = 10;
   private static boolean MASTERMIND_DEBUG_MODE = false;
   private static String MASTERMIND_NOM_USER = "Utilisateur";
   private static String MASTERMIND_NOM_ROBOT1 = "Chapi";
   private static String MASTERMIND_NOM_ROBOT2 = "Chapo";

   // Les tags pour passer des paramètres en arguments de la ligne de commande
   private static String MASTERMIND_TAG_DEBUG = "-d";
   private static String MASTERMIND_TAG_USER = "-u";
   private static String MASTERMIND_TAG_MAX_DIGIT = "-m";

   private int longueur;         // longueur du code à trouver
   private int nbEssai;          // nombre max d'essai
   private int maxDigit;         // longueur max autorisé du code. peut être changé avec : -m nouvelle_valeur
   private boolean isDebugMode;  // mode développeur. peut être activé avec : -d
   private String nomUser;       // nom utilisateur. peut être changé avec : -u nouveau_nom
   private String nomRobot1;     // nom du premier robot qui sert dans tous les modes
   private String nomRobot2;     // nom du second robot pour le mode autobaston
   private String tagDebug;      // tag pour activer le mode debug en ligne de commande
   private String tagUser;       // tag pour changer le nom de l'utilisateur
   private String tagMaxDigit;   // tag pour changet le nombre max de digit

   public MastermindProperties( String[] args){
      String sProp;

      // initialisation de la resource bundle
      bundle = ResourceBundle.getBundle(CONFIGURATION_FILE);

      // lecture des paramètres dans le fichier
      this.longueur = getInt("mastermind.combinaison.chiffres", MASTERMIND_LONGUEUR);
      this.nbEssai = getInt("mastermind.combinaison.essais", MASTERMIND_ESSAIS);
      this.maxDigit = getInt("mastermind.combinaison.maxdigit", MASTERMIND_MAX_DIGIT);
      this.isDebugMode = getBoolean("mastermind.mode.developpeur", MASTERMIND_DEBUG_MODE);
      this.nomUser = getString("mastermind.nom.user",MASTERMIND_NOM_USER);
      this.nomRobot1 = getString("mastermind.nom.robot1", MASTERMIND_NOM_ROBOT1);
      this.nomRobot2 = getString("mastermind.nom.robot2", MASTERMIND_NOM_ROBOT2);
      this.tagDebug = getString("mastermind.tag.debug", MASTERMIND_TAG_DEBUG);
      this.tagUser = getString("mastermind.tag.user", MASTERMIND_TAG_USER);
      this.tagMaxDigit = getString("mastermind.tag.maxdigit", MASTERMIND_TAG_MAX_DIGIT);

      // on écrase avec les propriétées passées en ligne de commande
      getArguments(args);
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
    * @param args arguments de la ligne de commande
    */
   private void getArguments(String[] args){

      // on récupère les paramètres en ligne de commande

         for(int i=0; i<args.length;i++){
         String option = args[i];

         if (this.tagDebug.equals(args[i])) {
            // c'est l'activation du mode développeur
            this.isDebugMode = true;
            System.out.println("Mode debug activé !");
            log.trace("Mode debug activé !");
         } else if (this.tagUser.equals(args[i])) {
            // récupération du nom de l'utilisateur
            if( args[i]!=null) {
               this.nomUser = args[++i];
               System.out.println("User name : " + getNomUser());
               log.trace("User name : " + getNomUser());
            }
         } else if (this.tagMaxDigit.equals(args[i])) {
            // récupération de la taille maximale
            if( args[++i]!=null) {
               try {
                  this.maxDigit = Integer.valueOf(args[i]);
                  System.out.println("MaxDigit : " + getMaxDigit());
                  log.trace("MaxDigit : " + getMaxDigit());
               } catch (NumberFormatException nfE) {
                  log.error("Mauvaise valeur de MaxDigit : " + args[i] + "\n" + nfE);
               }
            }
         } else {
            // affichage de l'aide en ligne
            System.out.println("Option non reconnue : "+args[i]);
            System.out.println("mastermind ["+ this.tagDebug+"]["+this.tagUser+" nom]["+ this.tagMaxDigit+" nombre]");
            System.out.println(this.tagDebug + " : activate debug");
            System.out.println(this.tagUser + " nom : set user name");
            System.out.println(this.tagMaxDigit + " nombre: set maxdigit");
            exit(0);
         }
      }
   }

   /**
    * getBoolean : méthode qui lit un booléen dans le bundel et retourne la valeur par défaut s'il n'y a pas de valeur
    *
    * @param key nom du paramètre dans le fichier properties
    * @param defaultValue valeur d'initialisation par défaut
    * @return  valeur lue ou celle par défaut
    */
  private  boolean getBoolean(String key, boolean defaultValue){
      String value;

      try {
         value = bundle.getString(key);
         return (value.equals("")) ? defaultValue : Boolean.valueOf(value);
      } catch (MissingResourceException mrE) {
         log.error(key + " absente du fichier de configuration " + mrE);
         return defaultValue;
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
      String value;

      try {
         value = bundle.getString(key);
         return (value.equals("")) ? defaultValue : Integer.valueOf(value);
      } catch (MissingResourceException mrE) {
         log.error(key + " absente du fichier de configuration " + mrE);
         return defaultValue;
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
      String value;

      try {
         value = bundle.getString(key);
         return (value.equals("")) ? defaultValue : value;
      } catch (MissingResourceException mrE) {
         log.error(key + " absente du fichier de configuration " + mrE);
         return defaultValue;
      }
   }

}
