package com.ocr.pedsf.controler.modes;

import com.ocr.pedsf.controler.Mode;
import com.ocr.pedsf.model.*;
import com.ocr.pedsf.model.personnages.Robot;
import com.ocr.pedsf.model.personnages.User;
import com.ocr.pedsf.vue.Resultat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Challenger class contrôleur pour le modes Challenger
 *
 * @author pedsf
 */
public class Challenger implements Mode {
   private static final Logger log = LogManager.getLogger(Challenger.class);

   private MastermindProperties mp;
   private boolean trouve = false;
   private int nbCoup = 0;


   public Challenger(MastermindProperties mp){
      this.mp = mp;
   }

   public void run() {
      log.traceEntry();
      System.out.println("\nMASTERMIND : Mode Challenger\n");

      // initialisation des protagonistes
      Personnage ia = new Robot("ia",mp);
      Personnage utilisateur = new User("utilisateur",mp);
      // initialisation des NombresSecrets
      ia.init();

      if (mp.isModeDeveloppeur())
         System.out.println("(Combinaison secrète : " + ia.getNs().getNombre() + ")\n");

      if(mp.getLongueur() == 1) {
         System.out.println("Trouvez un nombre à 1 chiffre.");
      } else {
         System.out.println("Trouvez un nombre à " + mp.getLongueur() + " chiffres.");
      }

      do {
         // l'utilisateur attaque l'ordinateur
         trouve = utilisateur.attack(ia);
         nbCoup++;
      } while (!trouve && nbCoup < mp.getNbEssai());

      if (nbCoup < mp.getNbEssai()) {
         Resultat.display(utilisateur.getNom(), ia.getNom(), nbCoup, ia.getNs().getNombre());
      } else {
         Resultat.display(ia.getNom(), utilisateur.getNom(), nbCoup, ia.getNs().getNombre());
      }

      log.traceExit();
   }
}
