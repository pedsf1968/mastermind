package com.ocr.pedsf.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * MastermindProperties : class pour gérer les propriétés du jeux
 *
 * @author pedsf
 */
public class MastermindProperties {
   private static final Logger log = LogManager.getLogger(MastermindProperties.class);

   private static int MASTERMIND_LONGUEUR = 4;
   private static int MASTERMIND_ESSAIS = 10;
   private static int MASTERMIND_MAX_DIGIT = 10;
   private static boolean MASTERMIND_DEVELOPPEUR = false;
   private static String MASTERMIND_TAG_DEBUG = "-d";

   private int longueur;
   private int nbEssai;
   private int maxDigit;
   private boolean modeDeveloppeur;
   private String tagDebug;

   public MastermindProperties(String fichier){
      Properties properties = new Properties();
      FileInputStream fis = null;
      String sProp;

      try {
         // lecture du fichier de propriétés
         fis = new FileInputStream(fichier);
         properties.load(fis);

      } catch (IOException e) {
         log.error(e);
      }

      //initialisation des propriétés
      sProp = properties.getProperty("mastermind.combinaison.chiffres");
      this.longueur = (sProp==null) ? MASTERMIND_LONGUEUR : Integer.valueOf(sProp);

      sProp = properties.getProperty("mastermind.combinaison.essais");
      this.nbEssai = (sProp==null) ? MASTERMIND_ESSAIS: Integer.valueOf(sProp);

      sProp = properties.getProperty("mastermind.combinaison.maxdigit");
      this.maxDigit = (sProp==null) ? MASTERMIND_MAX_DIGIT: Integer.valueOf(sProp);

      sProp = properties.getProperty("mastermind.mode.developpeur");
      this.modeDeveloppeur = (sProp==null) ? MASTERMIND_DEVELOPPEUR : Boolean.valueOf(sProp);

      sProp = properties.getProperty("mastermind.tag.debug");
      this.tagDebug = (sProp==null) ? MASTERMIND_TAG_DEBUG : sProp;

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

   public boolean isModeDeveloppeur() {
      return modeDeveloppeur;
   }

   public void setModeDeveloppeur(boolean modeDeveloppeur) {
      this.modeDeveloppeur = modeDeveloppeur;
   }

   public String getTagDebug() {

      return tagDebug;
   }

   public void setTagDebug(String tagDebug) {
      this.tagDebug = tagDebug;
   }

   @Override
   public String toString() {
      String reponse ="MastermindProperties{ " +
            "Longueur de la chaine = " + longueur +
            ", Nombre d'essais = " + nbEssai;
      reponse +=  (modeDeveloppeur) ? ", Mode développeur activé":", Mode normal" ;

      return reponse+ " }";
   }
}
