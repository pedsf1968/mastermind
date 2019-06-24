package com.ocr.pedsf;

import com.ocr.pedsf.controler.Jeu;
import com.ocr.pedsf.model.MastermindProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import static java.lang.System.exit;

/**
 * Main : class principale de lancement du jeu
 * @author pedsf
 */
public class Main {
   private static final Logger log = LogManager.getLogger(Main.class);
   // emplacement du fichier de configuration
   private static String CONFIGURATION_FILE = "src/main/resources/mastermind.properties";

   public static void main(String[] args){
      // récupération de la configuration
      MastermindProperties mp = gestionParametres(args);

      // lancement du jeu
      new Jeu(mp);
   }

   /**
    * gestionParametres : méthode qui récupère la configuration et les arguments
    *                     et retourne un objet du type MastermindProperties
    *
    * @param args arguments de la ligne de commande
    * @return objet du type MastermindProperties
    */
   private static MastermindProperties gestionParametres(String[] args){
      // récupération de la configuration du fichier properties
      MastermindProperties response = new MastermindProperties(CONFIGURATION_FILE);

      // on active le mode développeur s'il est tapé en ligne de commande
      for(int i=0; i<args.length;i++){
         String option = args[i];

         if (option.equals(response.getTagDebug())) {
            response.setModeDeveloppeur(true);
            System.out.println("Mode debug activé !");
         } else {
            // affichage de l'aide en ligne
            System.out.println("mastermind ["+ response.getTagDebug()+"]");
            System.out.println("-d : option debug");
            exit(0);
         }
      }
      return response;
   }

}
