package com.ocr.pedsf.model;

import com.ocr.pedsf.exceptions.ParametreIncorrectException;
import com.ocr.pedsf.model.codes.CodeFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * MastermindProperties : class for the game properties
 *                        read the properties file and the command line arguments
 *
 * @author pedsf
 * @version 1.0
 */
public class GameProperties {
   private static final Logger log = LogManager.getLogger(GameProperties.class);

   // nom du fichier de configuration
   private static final String MASTERMIND_PROPERTIES = "mastermind.properties";

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

   // variables pour le nombre secret
   private int length;         // longueur du code à trouver
   private int trials;          // nombre max d'essai
   private int maxLength;         // longueur max autorisé du code. peut être changé avec : -m nouvelle_valeur
   private boolean isDebugMode;  // mode développeur. peut être activé avec : -d
   private int gameType;

   // les noms des protagonistes
   private String userName;       // nom utilisateur. peut être changé avec : -u nouveau_nom
   private String robot1Name;     // nom du premier robot qui sert dans tous les modes
   private String robot2Name;     // nom du second robot pour le mode autobaston

   // les tags de la ligne de commande
   private String debugTag;      // tag to activate debug mode
   private String userTag;       // tag for setting user name
   private String maxLengthTag;   // tag for setting max code length
   private String trialsTag;     // tag for the number of trials

   public GameProperties() {
      //on récupère les propriétés dans le fichier
      readProperties();
   }

   public GameProperties(String[] args){
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

   public int getGameType() {
      return gameType;
   }

   public void setGameType(int gameType) {
      this.gameType = gameType;
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

      try (InputStream input = getClass().getClassLoader().getResourceAsStream(MASTERMIND_PROPERTIES)) {

         Properties prop = new Properties();

         if (input == null) {
            log.error("Fichier {} absent, initialisation avec les paramètres par défaut.", MASTERMIND_PROPERTIES);
            // initialisation avec les valeurs par défaut
            this.length = MASTERMIND_LENGTH;
            this.trials = MASTERMIND_TRIALS;
            this.maxLength = MASTERMIND_MAX_LENGTH;
            this.gameType = CodeFactory.CODE_SIMPLIFIED;
            this.isDebugMode = MASTERMIND_IS_DEBUG_MODE;
            this.userName = MASTERMIND_USER_NAME;
            this.robot1Name = MASTERMIND_ROBOT1_NAME;
            this.robot2Name = MASTERMIND_ROBOT2_NAME;
            this.debugTag =  MASTERMIND_DEBUG_TAG;
            this.userTag = MASTERMIND_USER_NAME_TAG;
            this.maxLengthTag = MASTERMIND_MAX_LENGTH_TAG;
            this.trialsTag = MASTERMIND_TRIALS_TAG;
            return;
         }

         prop.load(input);
         this.length = Integer.parseInt(prop.getProperty("mastermind.code.length",String.valueOf(MASTERMIND_LENGTH)));
         this.trials = Integer.parseInt(prop.getProperty("mastermind.code.trials", String.valueOf(MASTERMIND_TRIALS)));
         this.maxLength = Integer.parseInt(prop.getProperty("mastermind.code.maxlength", String.valueOf(MASTERMIND_MAX_LENGTH)));
         this.gameType = CodeFactory.CODE_SIMPLIFIED;

         this.isDebugMode = Boolean.parseBoolean(prop.getProperty("mastermind.debug.mode", String.valueOf(MASTERMIND_IS_DEBUG_MODE)));

         this.userName = prop.getProperty("mastermind.name.user",MASTERMIND_USER_NAME);
         this.robot1Name = prop.getProperty("mastermind.name.robot1", MASTERMIND_ROBOT1_NAME);
         this.robot2Name = prop.getProperty("mastermind.name.robot2", MASTERMIND_ROBOT2_NAME);

         this.debugTag = prop.getProperty("mastermind.tag.debug", MASTERMIND_DEBUG_TAG);
         this.userTag = prop.getProperty("mastermind.tag.user", MASTERMIND_USER_NAME_TAG);
         this.maxLengthTag = prop.getProperty("mastermind.tag.maxlength", MASTERMIND_MAX_LENGTH_TAG);
         this.trialsTag = prop.getProperty("mastermind.tag.trials",MASTERMIND_TRIALS_TAG);

      } catch (IOException ex) {
         log.error(ex.getMessage());

      }

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
            this.maxLength = Integer.parseInt(paramList.get(index));
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
            this.trials = Integer.parseInt(paramList.get(index));
            paramList.remove(index);
            paramList.remove(index-1);
            log.info("Nombre d'essais : {} ", getTrials());
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

}
