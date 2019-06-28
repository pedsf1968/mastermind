package com.ocr.pedsf.controler.modes;

import com.ocr.pedsf.controler.Mode;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.Personnage;
import com.ocr.pedsf.model.personnages.Robot;
import com.ocr.pedsf.model.personnages.User;
import com.ocr.pedsf.vue.Resultat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Defenseur class contrôleur pour le modes Defenseur
 *
 * @author pedsf
 */
public class Defenseur implements Mode {
   private static final Logger log = LogManager.getLogger(Defenseur.class);

   private MastermindProperties mp;
   private boolean trouve = false;
   private int nbCoup = 0;

   public Defenseur(MastermindProperties mp){
      this.mp = mp;
   }

   /**
    * run : méthode qui lance le modes défense
    */

   public void run(){
      log.traceEntry();
      System.out.println("\nMASTERMIND : Mode Defenseur\n");

      // initialisation des protagonistes
      Personnage ia = new Robot("ia",mp);
      Personnage utilisateur = new User("utilisateur",mp);
      // initialisation des NombresSecrets
      ia.init();
      utilisateur.init();

      if(mp.isModeDeveloppeur()) {
         System.out.println("\nIndiquez pour chaque chiffre de la combinaison proposée si" +
               " le chiffre de sa combinaison est :\n" +
               "plus grand par un (+), plus petit par un (-) identique par un (=)\n");
      }

      do {

         nbCoup++;
      } while (!ia.attack(utilisateur) && nbCoup<mp.getNbEssai());

      if (nbCoup>mp.getNbEssai()) {
         Resultat.display(utilisateur.getNom(), ia.getNom(), nbCoup, utilisateur.getNs().getNombre());
      } else {
         Resultat.display(ia.getNom(), utilisateur.getNom(),nbCoup, utilisateur.getNs().getNombre());
      }

      log.traceExit();
   }
}
