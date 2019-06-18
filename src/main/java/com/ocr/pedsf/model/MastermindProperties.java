package com.ocr.pedsf.model;

import com.ocr.pedsf.utils.PropertyLoader;

import java.io.IOException;
import java.util.Properties;

public class MastermindProperties {
   private static int MASTERMIND_LONGUEUR = 4;
   private static int MASTERMIND_ESSAIS = 10;
   private static boolean MASTERMIND_DEVELOPPEUR = false;
   private int longueur = MASTERMIND_LONGUEUR;
   private int nbEssai = MASTERMIND_ESSAIS;
   private boolean modeDeveloppeur = MASTERMIND_DEVELOPPEUR;

   public MastermindProperties(String fichier){
      Properties prop = null;
      try {
         prop = PropertyLoader.load(fichier);
      } catch (
            IOException e) {
         e.printStackTrace();
      }

      this.longueur = Integer.valueOf(prop.getProperty("combinaison.chiffres"));
      this.nbEssai = Integer.valueOf(prop.getProperty("combinaison.essais"));
      this.modeDeveloppeur = Boolean.valueOf(prop.getProperty("mode.developpeur"));
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

   public boolean isModeDeveloppeur() {
      return modeDeveloppeur;
   }

   public void setModeDeveloppeur(boolean modeDeveloppeur) {
      this.modeDeveloppeur = modeDeveloppeur;
   }

   @Override
   public String toString() {
      return "MastermindProperties{" +
            "Longueur de la chaine =" + longueur +
            ", Nombre d'essais =" + nbEssai +
            ", Mode développeur activé =" + modeDeveloppeur +
            '}';
   }
}
