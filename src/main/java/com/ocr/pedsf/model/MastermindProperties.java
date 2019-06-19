package com.ocr.pedsf.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * MastermindProperties : class pour gérer les propriétés du jeux
 */
public class MastermindProperties {
   private static int MASTERMIND_LONGUEUR = 4;
   private static int MASTERMIND_ESSAIS = 10;
   private static int MASTERMIND_MAX_DIGIT = 10;
   private static boolean MASTERMIND_DEVELOPPEUR = false;

   private int longueur;
   private int nbEssai;
   private int maxDigit;
   private boolean modeDeveloppeur;

   public MastermindProperties(String fichier){
      Properties properties = new Properties();
      FileInputStream fis = null;
      String sProp;

      try {
         // lecture du fichier de propriétés
         fis = new FileInputStream(fichier);
         properties.load(fis);

      } catch (
            IOException e) {
         e.printStackTrace();
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

   @Override
   public String toString() {
      String reponse ="MastermindProperties{ " +
            "Longueur de la chaine = " + longueur +
            ", Nombre d'essais = " + nbEssai;
      reponse +=  (modeDeveloppeur) ? ", Mode développeur activé":", Mode normal" ;

      return reponse+ " }";
   }
}
