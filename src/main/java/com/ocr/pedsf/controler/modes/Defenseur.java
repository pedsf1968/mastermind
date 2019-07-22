package com.ocr.pedsf.controler.modes;

import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.personnages.Personnage;
import com.ocr.pedsf.model.personnages.Robot;
import com.ocr.pedsf.model.personnages.User;
import com.ocr.pedsf.vue.Resultat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Defenseur : class implémentant Mode qui sert de contrôleur pour le modes Defenseur
 *
 * @author pedsf
 * @version 1.0
 */
public class Defenseur implements Mode {
   private static final Logger log = LogManager.getLogger(Defenseur.class);

   private MastermindProperties mp;
   private int nbCoup = 0;

   public Defenseur(MastermindProperties mp){
      this.mp = mp;
   }

   /**
    * run : méthode qui lance le modes défense
    */
   public void run(){
      log.traceEntry();
      log.info("MASTERMIND : Mode Defenseur\n");

      // initialisation des protagonistes
      Personnage ia = new Robot(mp.getNomRobot1(),mp);
      Personnage utilisateur = new User(mp.getNomUser(),mp);
      // initialisation des NombresSecrets
      ia.init();
      utilisateur.init();

      if(mp.isDebugMode()) {
         log.info("\nIndiquez pour chaque chiffre de la combinaison proposée si" +
               " le chiffre de sa combinaison est :\n" +
               "plus grand par un (+), plus petit par un (-) identique par un (=)\n");
      }

      do {
         nbCoup++;
      } while (!ia.attack(utilisateur) && nbCoup<mp.getNbEssai());

      if (nbCoup>mp.getNbEssai()) {
         // l'adversaire n'a pas trouvé la solution
         Resultat.display(utilisateur.getNom(), ia.getNom(), nbCoup, utilisateur.getNs().getNombre());
      } else {
         // l'adversaire a trouvé la réponse dans la limite du nombre de coups
         Resultat.display(ia.getNom(), utilisateur.getNom(),nbCoup, utilisateur.getNs().getNombre());
      }

      log.traceExit();
   }
}
