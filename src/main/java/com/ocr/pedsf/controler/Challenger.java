package com.ocr.pedsf.controler;

import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.NombreSecret;
import com.ocr.pedsf.vue.DemandeProposition;
import com.ocr.pedsf.vue.Resultat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Challenger class contrôleur pour le mode Challenger
 *
 * @author pedsf
 */
public class Challenger implements Mode{
   private static final Logger log = LogManager.getLogger(Challenger.class);

   private MastermindProperties mp;
   private boolean trouve = false;
   private int nbCoup = 1;
   private String proposition = "";


   public Challenger(MastermindProperties mp){
      this.mp = mp;
   }

   public void run() {
      log.debug("Lancement du mode Challenger");
      System.out.println("\nMASTERMIND : Mode Challenger\n");

      // initialisation du nombre de l'ordinateur
      NombreSecret nso = new NombreSecret(mp.getLongueur());
      NombreSecret nsu = new NombreSecret(mp.getLongueur());

      if (mp.isModeDeveloppeur())
         System.out.println("(Combinaison secrète : " + nso.getNombre() + ")");

      if(mp.getLongueur() == 1) {
         System.out.println("Trouvez un nombre à 1 chiffre.");
      } else {
         System.out.println("Trouvez un nombre à " + mp.getLongueur() + " chiffres.");
      }

      do {
         // l'utilisateur demande à l'ordinateur
         if(mp.isModeDeveloppeur()) {
            System.out.print("Proposition (" + nso.getNombre() + ") : ");
         } else {
            System.out.print("Proposition : ");
         }

         nsu.setNombre(DemandeProposition.get(mp.getLongueur()));

         try {
            System.out.println("Proposition : " + nsu.getNombre() + " -> Réponse IA : " + nso.test(nsu));
            if (nsu.equals(nso))
               trouve = true;

         } catch (TailleDifferenteException e) {
            log.error(e);
         }
      } while (!trouve && ++nbCoup < mp.getNbEssai());

      if (nbCoup < mp.getNbEssai()) {
         Resultat.display("Utilisateur", "IA", nbCoup, nso.getNombre());
         log.debug("Utilisateur", "IA", nbCoup, nso.getNombre());
      } else {
         Resultat.display("IA", "Utilisateur", nbCoup, nso.getNombre());
         log.debug("IA", "Utilisateur", nbCoup, nso.getNombre());
      }
   }
}
