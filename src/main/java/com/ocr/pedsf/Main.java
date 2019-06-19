package com.ocr.pedsf;

import com.ocr.pedsf.controler.Jeu;
import com.ocr.pedsf.model.MastermindProperties;

public class Main {
   // emplacement du fichier de configuration
   private static String CONFIGURATION_FILE = "src/main/resources/mastermind.properties";


   public static void main(String[] args){
      // récupération de la configuration
      MastermindProperties mp = new MastermindProperties(CONFIGURATION_FILE);
      // lancement du jeu
      Jeu jeu = new Jeu(mp);
   }

}
