package com.ocr.pedsf.controler.modes;

import com.ocr.pedsf.model.*;
import com.ocr.pedsf.model.personnages.Personnage;
import com.ocr.pedsf.model.personnages.Robot;
import com.ocr.pedsf.model.personnages.User;
import com.ocr.pedsf.vue.Resultat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Challenger : class implémentant Mode qui sert de contrôleur pour le modes Challenger
 *
 * @author pedsf
 * @version 1.0
 */
public class Challenger implements Mode {
   private static final Logger log = LogManager.getLogger(Challenger.class);

   private MastermindProperties mp;
   private int nbCoup = 0;


   public Challenger(MastermindProperties mp){
      this.mp = mp;
   }


   public void run() {
      log.traceEntry();
      boolean trouve;

      log.info("MASTERMIND : Mode Challenger\n");

      // initialisation des protagonistes
      Personnage ia = new Robot(mp.getNomRobot1(),mp);
      Personnage utilisateur = new User(mp.getNomUser(),mp);
      // initialisation des NombresSecrets
      ia.init();

      if (mp.isDebugMode())
         log.info("(Combinaison secrète : {})\n",ia.getNs().getNombre());

      if(mp.getLongueur() == 1) {
         log.info("Trouvez un nombre à 1 chiffre.\n");
      } else {
         log.info("Trouvez un nombre à {} chiffres.\n", mp.getLongueur());
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
