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

   /**
    * run : méthode qui lance le duel
    */
   public void run(){
      log.debug("Lancement du mode Duel");
      System.out.println("\nMASTERMIND : Mode Duel\n");
      String reponse = "";
      boolean badResponse = true;

      // initialisation du nombre secret de l'ordinateur
      NombreSecret nso = new NombreSecret(mp.getLongueur());
      // initialisation de la première proposition de l'ordinateur
      IA ia = new IA(mp.getLongueur());

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
      System.out.println("Votre nombre secret est : " + nsu.getNombre()+"\n");
      // initialisation de la proposition de l'utilisateur
      NombreSecret nspu = new NombreSecret(mp.getLongueur());

      do {
         // l'utilisateur demande à l'ordinateur
         System.out.print("Proposition : ");
         // On place la proposition de l'utilisateur dans un NombreSecret
         nspu.setNombre( DemandeProposition.get(mp.getLongueur()));

         try {
            // on demande à l'ordinateur de tester notre proposition par rapport à son NombreSecret
            // c'est pour avoir l'affichage des +-=
            reponse = nso.test(nspu);
            if(mp.isModeDeveloppeur()) {
               System.out.println("Vous ("+ nso.getNombre() +") proposition : " + nspu.getNombre() + " -> réponse IA : " + reponse);
            } else {
               System.out.println("Vous proposition : " + nspu.getNombre() + " -> réponse IA : " + reponse);
            }
         } catch (TailleDifferenteException e) {
            log.error(e);
         }

         // on teste si on a trouvé le NombreSecret et on sort du duel si on a la bonne réponse
         if(nso.equals(nspu)) {
            trouve = true;
            Resultat.display("Utilisateur", "IA",nbCoup,nso.getNombre());
         } else {
            // si la réponse n'est pas trouvée c'est au tour de l'ordinateur

            badResponse=true;

            do {
               // on boucle si l'utilisateur c'est trompé en tappant une mauvaise réponse +-= à l'ordinateur
               System.out.print("IA (" + nsu.getNombre() +") proposition : " + ia.getNombreSecret() + " -> votre réponse : ");
               reponse = DemandeReponse.get(mp.getLongueur(), mp.isModeDeveloppeur());

               try {
                  // Si la réponse de l'utilisateur est correcte on sort de la boucle
                  if( reponse.equals(nsu.test(ia.getNs()))) badResponse = false;

               } catch (TailleDifferenteException e) {
                  log.error(e);
               }
            }while(badResponse);

            // on teste si l'ordinateur à trouvé la bonne réponse
            if(nsu.equals(ia.getNs())) {
               trouve = true;
               Resultat.display( "IA","Utilisateur",nbCoup,nso.getNombre());
            }

            try {
               // l'ordinateur calcule sa proposition suivante en fonction de la réponse de l'utiulisateur
               ia.proposition(reponse);
            } catch (BornageException | TailleDifferenteException e) {
               log.error(e);
            }

            // incrémentation du nombre de tour
            this.nbCoup++;

         }
      } while (!trouve);
   }
}
