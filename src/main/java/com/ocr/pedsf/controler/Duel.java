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
 * Duel class contrôleur pour le mode Duel
 *
 * @author pedsf
 */
public class Duel {
   private static final Logger log = LogManager.getLogger(Duel.class);

   private MastermindProperties mp;
   private boolean trouve;
   private int nbCoup;
   private String proposition;
   private String reponse;

   public Duel(MastermindProperties mp){
      this.mp = mp;
      this.trouve = false;
      this.nbCoup = 1;
      this.proposition = "";
      this.reponse = "";
   }

   public void setMp(MastermindProperties mp) {
      this.mp = mp;
   }

   public void run(){
      log.debug("Lancement du mode Duel");
      System.out.println("\nMASTERMIND : Mode Duel\n");
      String proposition = "";
      String reponse = "";
      boolean badResponse = true;

      // initialisation du nombre secret de l'ordinateur
      NombreSecret nso = new NombreSecret(mp.getLongueur());

      if(mp.isModeDeveloppeur())
         System.out.println("(Combinaison secrète IA : " + nso.getNombre()+")");

      // saisie du code de départ par l'utilisateur
      if(mp.getLongueur()==1) {
         System.out.print("Entrez votre nombre secret de 1 chiffre : ");
      } else {
         System.out.print("Entrez votre nombre secret de " + mp.getLongueur() + " chiffres : ");
      }


      // saisie du code secret de l'utilisateur
      NombreSecret nsu = new NombreSecret(DemandeProposition.get(mp.getLongueur()));
      // initialisation de la proposition de l'utilisateur
      NombreSecret nspu = new NombreSecret(mp.getLongueur());

      IA ia = new IA(mp.getLongueur());

      do {
         // l'utilisateur demande à l'ordinateur
         if(mp.isModeDeveloppeur()) {
            System.out.print("Proposition (" + nso.getNombre() + ") : ");
         } else {
            System.out.print("Proposition : ");
         }

         nspu.setNombre( DemandeProposition.get(mp.getLongueur()));

         try {
            reponse = nso.test(nspu);
            System.out.println("Vous : proposition : " + nspu.getNombre() + " -> Réponse IA : " + reponse);
         } catch (TailleDifferenteException e) {
            log.error(e);
         }

         if(nso.equals(nspu)) {
            trouve = true;
            Resultat.display("Utilisateur", "IA",nbCoup,nso.getNombre());
         } else {
            // si la réponse n'est pas trouvée c'est au tour de l'ordinateur

            badResponse=true;
            do {
               System.out.print("Votre code : " + nsu.getNombre() +" proposition de l'IA : " + ia.getNombreSecret() + " réponse : ");
               reponse = DemandeReponse.get(mp.getLongueur(), mp.isModeDeveloppeur());

               try {
                  // on recommence tant qu'il y a une mauvaise réponse
                  if( reponse.equals(nsu.test(ia.getNs()))) badResponse = false;

               } catch (TailleDifferenteException e) {
                  log.error(e);
               }

            }while(badResponse);



            if(nsu.equals(ia.getNs())) {
               trouve = true;
               Resultat.display( "IA","Utilisateur",nbCoup,nso.getNombre());
            }

            try {
               ia.proposition(reponse);
            } catch (BornageException e) {
               // IA repart de zéro car il y a eu une erreur de saisie
               this.nbCoup=0;
               ia = new IA(mp.getLongueur());
               log.error(e);
            } catch (TailleDifferenteException e) {
               log.error(e);
            }

            this.nbCoup++;

         }
      } while (!trouve);
   }
}
