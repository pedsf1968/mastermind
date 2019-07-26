package com.ocr.pedsf.model;

import com.ocr.pedsf.exceptions.ParametreIncorrectException;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


/**
 * MastermindProperties : class for the game properties
 *                        read the properties file and the command line arguments
 *
 * @author pedsf
 * @version 1.0
 */
public class MastermindProperties {
   private static final Logger log = LogManager.getLogger(MastermindProperties.class);
   private static final String MESSAGE_NO_VALUE = "no {} value in configuration file";

   // nom du fichier de configuration
   private static final String MASTERMIND_PROPERTIES = "mastermind";

   // constantes pour le nombre secret
   private static final int MASTERMIND_LENGTH = 4;
   private static final int MASTERMIND_TRIALS = 10;
   private static final int MASTERMIND_MAX_LENGTH = 10;
   private static final boolean MASTERMIND_IS_DEBUG_MODE = false;

   // les noms des protagonistes par défaut
   private static final String MASTERMIND_USER_NAME = "User";
   private static final String MASTERMIND_ROBOT1_NAME = "Chapi";
   private static final String MASTERMIND_ROBOT2_NAME = "Chapo";

   // Les tags par défaut pour passer des paramètres en arguments de la ligne de commande
   private static final String MASTERMIND_DEBUG_TAG = "-d";
   private static final String MASTERMIND_USER_NAME_TAG = "-u";
   private static final String MASTERMIND_MAX_LENGTH_TAG = "-m";
   private static final String MASTERMIND_TRIALS_TAG = "-t";

   // variable pour lire les paramètres dans le fichier properties
   private ResourceBundle bundle;

   // variables pour le nombre secret
   private int length;         // longueur du code à trouver
   private int trials;          // nombre max d'essai
   private int maxLength;         // longueur max autorisé du code. peut être changé avec : -m nouvelle_valeur
   private boolean isDebugMode;  // mode développeur. peut être activé avec : -d

   // les noms des protagonistes
   private String userName;       // nom utilisateur. peut être changé avec : -u nouveau_nom
   private String robot1Name;     // nom du premier robot qui sert dans tous les modes
   private String robot2Name;     // nom du second robot pour le mode autobaston

   // les tags de la ligne de commande
   private String debugTag;      // tag to activate debug mode
   private String userTag;       // tag for setting user name
   private String maxLengthTag;   // tag for setting max code length
   private String trialsTag;     // tag for the number of trials

   public MastermindProperties() {
      //on récupère les propriétés dans le fichier
      readProperties();
   }

   public MastermindProperties(String[] args){
      //on récupère les propriétés dans le fichier
      readProperties();

      // on écrase avec les propriétées passées en ligne de commande
      List<String> paramList = new ArrayList<>(Arrays.asList(args));
      getArguments(paramList);

   }

   public int getLength() {
      return length;
   }

   public void setLength(int length) {
      this.length = length;
   }

   public int getTrials() {
      return trials;
   }

   public void setTrials(int trials) {
      this.trials = trials;
   }

   public int getMaxLength() {
      return maxLength;
   }

   public void setMaxLength(int maxLength) {
      this.maxLength = maxLength;
   }

   public boolean isDebugMode() {
      return isDebugMode;
   }

   public void setDebugMode(boolean debugMode) {
      isDebugMode = debugMode;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getRobot1Name() {
      return robot1Name;
   }

   public void setRobot1Name(String robot1Name) {
      this.robot1Name = robot1Name;
   }

   public String getRobot2Name() {
      return robot2Name;
   }

   public void setRobot2Name(String robot2Name) {
      this.robot2Name = robot2Name;
   }

   /**
    * readProperties : méthode qui récupère les propriétés dans le fichier
    */
   private void readProperties(){
      // initialisation de la resource bundle
      bundle = ResourceBundle.getBundle(MASTERMIND_PROPERTIES);

      // lecture des paramètres dans le fichier
      this.length = getInt("mastermind.code.length", MASTERMIND_LENGTH);
      this.trials = getInt("mastermind.code.trials", MASTERMIND_TRIALS);
      this.maxLength = getInt("mastermind.code.maxlength", MASTERMIND_MAX_LENGTH);

      this.isDebugMode = getBoolean("mastermind.debug.mode", MASTERMIND_IS_DEBUG_MODE);

      this.userName = getString("mastermind.name.user",MASTERMIND_USER_NAME);
      this.robot1Name = getString("mastermind.name.robot1", MASTERMIND_ROBOT1_NAME);
      this.robot2Name = getString("mastermind.name.robot2", MASTERMIND_ROBOT2_NAME);

      this.debugTag = getString("mastermind.tag.debug", MASTERMIND_DEBUG_TAG);
      this.userTag = getString("mastermind.tag.user", MASTERMIND_USER_NAME_TAG);
      this.maxLengthTag = getString("mastermind.tag.maxlength", MASTERMIND_MAX_LENGTH_TAG);
      this.trialsTag = getString("mastermind.tag.trials",MASTERMIND_TRIALS_TAG);
   }

   /**
    * getArguments : méthode qui récupère les paramètres en ligne de commande
    *
    * @param paramList arguments de la ligne de commande
    */
   private void getArguments(List<String> paramList) {
      log.traceEntry();
      int index = 0;
      // on récupère les paramètres en ligne de commande

      if (paramList.contains(this.debugTag)){
         // c'est l'activation du mode développeur
         this.isDebugMode = true;
         paramList.remove(this.debugTag);
         log.info("Mode debug activé !");
      }

      // récupération du nom de l'utilisateur
      if( paramList.contains(this.userTag)){
         index = paramList.indexOf(this.userTag) + 1;
         this.userName = paramList.get(index);
         paramList.remove(index);
         paramList.remove(index-1);
         log.info("User name : {}",getUserName());
      }

      // récupération de la taille maximale
      if(paramList.contains(this.maxLengthTag)) {
         try {
            index = paramList.indexOf(this.maxLengthTag) + 1;
            this.maxLength = Integer.valueOf(paramList.get(index));
            paramList.remove(index);
            paramList.remove(index-1);
            log.info("Taille maximale du code : {}",getMaxLength());
         } catch (NumberFormatException nfE) {
            log.error("Mauvaise valeur {} de taille maximale !",paramList.get(index));
         }
      }

      // get number of trials
      if(paramList.contains(this.trialsTag)) {
         try {
            index = paramList.indexOf(this.trialsTag) + 1;
            this.trials = Integer.valueOf(paramList.get(index));
            paramList.remove(index);
            paramList.remove(index-1);
            log.info("Nombre d'essais : " + getTrials());
         } catch (NumberFormatException nfE) {
            log.error("Mauvaise valeur {} d'essais !",paramList.get(index));
         }
      }

      // si la liste n'est pas vide il y a des paramètres en double ou des erreurs
      if(!paramList.isEmpty()
            &&( !StringUtils.equals(paramList.get(0),this.debugTag)
            || !StringUtils.equals(paramList.get(0),this.userTag)
            || !StringUtils.equals(paramList.get(0),this.maxLengthTag)
            || !StringUtils.equals(paramList.get(0),this.trialsTag))) {
         try {
            throw new ParametreIncorrectException();
         } catch (ParametreIncorrectException e) {

            // affichage de l'aide en ligne
            log.info("\nOption non reconnue : {}\n", paramList.get(0));
            log.info("mastermind [{}][{} nom][{} nombre][{} nombre]", this.debugTag,this.userTag,this.maxLengthTag,this.trialsTag);
            log.info("{} : active le mode debug",this.debugTag);
            log.info("{} nom : spécifie le nom de l'utilisateur",this.userTag);
            log.info("{} nombre: spécifie la longueur maximale du code",this.maxLengthTag);
            log.info("{} nombre: spécifie le nombre d'essais\n",this.trialsTag);
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
         log.error(MESSAGE_NO_VALUE, key);
         return log.traceExit(defaultValue);
      }
   }

}
