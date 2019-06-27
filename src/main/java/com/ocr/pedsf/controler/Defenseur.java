package com.ocr.pedsf.controler;

import com.ocr.pedsf.exceptions.BornageException;
import com.ocr.pedsf.exceptions.MauvaiseReponseException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.NombreSecret;
import com.ocr.pedsf.vue.DemandeProposition;
import com.ocr.pedsf.vue.DemandeReponse;
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
   private int nbCoup = 1;
   private String reponse = "";

   public Defenseur(MastermindProperties mp){
      this.mp = mp;
   }

   /**
    * run : méthode qui lance le mode défense
    */

   public void run(){
      log.debug("Lancement du mode Défenseur");
      System.out.println("\nMASTERMIND : Mode Defenseur\n");

      // saisie du code de départ par l'utilisateur
      if(mp.getLongueur()==1) {
         System.out.print("Entrez votre nombre secret de 1 chiffre : ");
      } else {
         System.out.print("Entrez votre nombre secret de " + mp.getLongueur() + " chiffres : ");
      }
      NombreSecret nsu = new NombreSecret(DemandeProposition.get(mp.getLongueur()));
      System.out.println("Votre nombre secret est : " + nsu.getNombre()+"\n");

      IA ia = new IA(mp.getLongueur());
      if(mp.isModeDeveloppeur()) {
         System.out.println("\nIndiquez pour chaque chiffre de la combinaison proposée si" +
               " le chiffre de sa combinaison est :\n" +
               "plus grand par un (+), plus petit par un (-) identique par un (=)\n");
      }

      do {
         System.out.print("Votre code : " + nsu.getNombre() + " proposition de l'IA : " + ia.getNombreSecret() + " réponse : ");
         reponse = DemandeReponse.get(mp.getLongueur(),mp.isModeDeveloppeur());

         try {
            if (!reponse.equals(nsu.test(ia.getNs())))
               throw new MauvaiseReponseException();

            if (nsu.equals(ia.getNs())) {
               trouve = true;
            } else {
               ia.proposition(reponse);
               this.nbCoup++;
            }
         } catch (MauvaiseReponseException | TailleDifferenteException | BornageException e) {
            log.error(e);
         }

      } while (!trouve && nbCoup<mp.getNbEssai());

      if (nbCoup>mp.getNbEssai()) {
         Resultat.display("Utilisateur", "IA", nbCoup, nsu.getNombre());
         log.debug("Utilisateur", "IA", nbCoup, nsu.getNombre());
      } else {
         Resultat.display("IA", "Utilisateur",nbCoup,nsu.getNombre());
         log.debug("IA", "Utilisateur",nbCoup,nsu.getNombre());
      }

   }
}
