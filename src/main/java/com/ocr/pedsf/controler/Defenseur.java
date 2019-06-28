package com.ocr.pedsf.controler;

import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.vue.Resultat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Defenseur class contrôleur pour le mode Defenseur
 *
 * @author pedsf
 */
public class Defenseur implements Mode{
   private static final Logger log = LogManager.getLogger(Defenseur.class);

   private MastermindProperties mp;
   private boolean trouve = false;
   private int nbCoup = 0;

   public Defenseur(MastermindProperties mp){
      this.mp = mp;
   }

   /**
    * run : méthode qui lance le mode défense
    */

   public void run(){
      log.debug("Lancement du mode Défenseur");
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
         trouve = ia.attack(utilisateur);
         nbCoup++;
      } while (!trouve && nbCoup<mp.getNbEssai());

      if (nbCoup>mp.getNbEssai()) {
         Resultat.display(utilisateur.getNom(), ia.getNom(), nbCoup, utilisateur.getNs().getNombre());
         log.debug(utilisateur.getNom(), ia.getNom(), nbCoup, utilisateur.getNs());
      } else {
         Resultat.display(ia.getNom(), utilisateur.getNom(),nbCoup, utilisateur.getNs().getNombre());
         log.debug(ia.getNom(), utilisateur.getNs(),nbCoup, utilisateur.getNs().getNombre());
      }

   }
}
