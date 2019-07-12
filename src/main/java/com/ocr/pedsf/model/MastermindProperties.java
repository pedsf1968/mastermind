package com.ocr.pedsf.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
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

      ResourceBundle bundle = ResourceBundle.getBundle(CONFIGURATION_FILE);

      //initialisation des propriétés
      sProp = bundle.getString("mastermind.combinaison.chiffres");
      this.longueur = (sProp.equals("")) ? MASTERMIND_LONGUEUR : Integer.valueOf(sProp);

      sProp = bundle.getString("mastermind.combinaison.essais");
      this.nbEssai = (sProp.equals("")) ? MASTERMIND_ESSAIS: Integer.valueOf(sProp);

      sProp = bundle.getString("mastermind.combinaison.maxdigit");
      this.maxDigit = (sProp.equals("")) ? MASTERMIND_MAX_DIGIT: Integer.valueOf(sProp);

      sProp = bundle.getString("mastermind.mode.developpeur");
      this.isDebugMode = (sProp.equals("")) ? MASTERMIND_DEBUG_MODE : Boolean.valueOf(sProp);

      sProp = bundle.getString("mastermind.nom.user");
      this.nomUser = (sProp.isEmpty()) ? MASTERMIND_NOM_USER : sProp;

      sProp = bundle.getString("mastermind.nom.robot1");
      this.nomRobot1 = (sProp.equals("")) ? MASTERMIND_NOM_ROBOT1 : sProp;

      sProp = bundle.getString("mastermind.nom.robot2");
      this.nomRobot2 = (sProp.equals("")) ? MASTERMIND_NOM_ROBOT2 : sProp;

      sProp = bundle.getString("mastermind.tag.debug");
      this.tagDebug = (sProp.equals("")) ? MASTERMIND_TAG_DEBUG : sProp;

      sProp = bundle.getString("mastermind.tag.user");
      this.tagUser = (sProp.equals("")) ? MASTERMIND_TAG_USER : sProp;

      sProp = bundle.getString("mastermind.tag.maxdigit");
      this.tagMaxDigit = (sProp.equals("")) ? MASTERMIND_TAG_MAX_DIGIT : sProp;

      // on écrase avec les propriétées passées en ligne de commande
      getArguments(args);
   }

   public int getLongueur() {
      return longueur;
   }

   public void setLongueur(int longueur) {
      this.longueur = longueur;
   }

   public int getNbEssai() {
      return nbEssai;
   }

   public void setNbEssai(int nbEssai) {
      this.nbEssai = nbEssai;
   }

   public int getMaxDigit() {
      return maxDigit;
   }

   public void setMaxDigit(int maxDigit) {
      this.maxDigit = maxDigit;
   }

   public boolean isDebugMode() {
      return isDebugMode;
   }

   public void setDebugMode(boolean debugMode) {
      isDebugMode = debugMode;
   }

   public String getNomUser() {
      return nomUser;
   }

   public void setNomUser(String nomUser) {
      this.nomUser = nomUser;
   }

   public String getNomRobot1() {
      return nomRobot1;
   }

   public void setNomRobot1(String nomRobot1) {
      this.nomRobot1 = nomRobot1;
   }

   public String getNomRobot2() {
      return nomRobot2;
   }

   public void setNomRobot2(String nomRobot2) {
      this.nomRobot2 = nomRobot2;
   }

   public String getTagDebug() {
      return tagDebug;
   }

   public void setTagDebug(String tagDebug) {
      this.tagDebug = tagDebug;
   }

   public String getTagUser() {
      return tagUser;
   }

   public void setTagUser(String tagUser) {
      this.tagUser = tagUser;
   }

   public String getTagMaxDigit() {
      return tagMaxDigit;
   }

   public void setTagMaxDigit(String tagMaxDigit) {
      this.tagMaxDigit = tagMaxDigit;
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
            setDebugMode(true);
            log.trace("Mode debug activé !");
         } else if (this.tagUser.equals(args[i++])) {
            // récupération du nom de l'utilisateur
            if( args[i]!=null) {
               setNomUser(args[i]);
               log.trace("User name : " + getNomUser());
            }
         } else if (this.tagMaxDigit.equals(args[i++])) {
            // récupération de la taille maximale
            if( args[i]!=null) {
               try {
                  setMaxDigit(Integer.valueOf(args[i]));
                  log.trace("MaxDigit : " + getMaxDigit());
               } catch (NumberFormatException nfE) {
                  log.error("Mauvaise valeur de MaxDigit : " + args[i] + "\n" + nfE);
               }
            }
         } else {
            // affichage de l'aide en ligne
            System.out.println("mastermind ["+ this.tagDebug+"]");
            System.out.println("-d : activate debug");
            System.out.println("-u nom : set user name");
            System.out.println("-m nombre: set maxdigit");
            exit(0);
         }
      }
   }

}
