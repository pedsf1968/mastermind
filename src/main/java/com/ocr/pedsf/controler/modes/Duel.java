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
 * Duel class contrôleur pour le modes Duel
 *
 * @author pedsf
 */
public class Duel implements Mode {
   private static final Logger log = LogManager.getLogger(Duel.class);

   private MastermindProperties mp;
   private boolean trouve = false;
   private int nbCoup = 0;

   public Duel(MastermindProperties mp){
      this.mp = mp;
   }

   public void setMp(MastermindProperties mp) {
      this.mp = mp;
   }

   /**
    * run : méthode qui lance le duel
    */
   public void run(){
      log.traceEntry();
      System.out.println("\nMASTERMIND : Mode Duel\n");

      // initialisation des protagonistes
      Personnage ia = new Robot("ia",mp);
      Personnage utilisateur = new User("utilisateur",mp);
      // initialisation des NombresSecrets
      ia.init();
      utilisateur.init();

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
