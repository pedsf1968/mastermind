package com.ocr.pedsf;

import com.ocr.pedsf.controler.Jeu;
import com.ocr.pedsf.model.MastermindProperties;

import static java.lang.System.exit;

public class Main {
   // emplacement du fichier de configuration
   private static String CONFIGURATION_FILE = "src/main/resources/mastermind.properties";


   public static void main(String[] args){
    // récupération de la configuration
      MastermindProperties mp = new MastermindProperties(CONFIGURATION_FILE);

      // on active le mode développeur s'il est tapé en ligne de commande
      for(int i=0; i<args.length;i++){
         String option = args[i];

         if (option.equals("-d")) {
            mp.setModeDeveloppeur(true);
            System.out.println("Mode debug activé !");
         } else {
            System.out.println("mastermind [-d]");
            System.out.println("-d : option debug");
            exit(0);
         }
      }
      // lancement du jeu
      Jeu jeu = new Jeu(mp);
   }

}
