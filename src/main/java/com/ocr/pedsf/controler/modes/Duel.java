package com.ocr.pedsf.controler.modes;

import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.personnages.Personnage;
import com.ocr.pedsf.model.personnages.Robot;
import com.ocr.pedsf.model.personnages.User;
import com.ocr.pedsf.vue.Resultat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Duel : class  implémentant Mode qui sert de contrôleur pour le modes Duel
 *
 * @author pedsf
 * @version 1.0
 */
public class Duel implements Mode {
   private static final Logger log = LogManager.getLogger(Duel.class);

   private MastermindProperties mp;
   private int nbCoup = 0;

   public Duel(MastermindProperties mp){
      this.mp = mp;
   }

   /**
    * run : méthode qui lance le duel
    */
   public void run(){
      log.traceEntry();
      boolean trouve;

      log.info("MASTERMIND : Mode Duel\n");

      // initialisation des protagonistes
      Personnage ia = new Robot(mp.getNomRobot1(),mp);
      Personnage utilisateur = new User(mp.getNomUser(),mp);
      // initialisation des NombresSecrets
      ia.init();
      utilisateur.init();

      if (mp.isDebugMode())
         log.info("(Combinaison secrète : {})\n",ia.getNs().getNombre());

      if(mp.getLongueur() == 1) {
         log.info("Trouvez un nombre à 1 chiffre.");
      } else {
         log.info("Trouvez un nombre à {} chiffres.", mp.getLongueur());
      }

      do {
         // l'utilisateur attaque l'ordinateur
         trouve = utilisateur.attack(ia);

         // au tour de l'ordinateur si la réponse n'est pas bonne
         if(!trouve)
            trouve = ia.attack(utilisateur);
         nbCoup++;
      } while (!trouve);

      if (ia.getNs().equals(utilisateur.getNsToSearch())) {
         Resultat.display(utilisateur.getNom(), ia.getNom(), nbCoup, ia.getNs().getNombre());
      } else {
         Resultat.display(ia.getNom(), utilisateur.getNom(), nbCoup, ia.getNs().getNombre());
      }

      log.traceExit();
   }
}
