package com.ocr.pedsf.model.personnages;

import com.ocr.pedsf.exceptions.CaractereIncorrectException;
import com.ocr.pedsf.exceptions.MauvaiseReponseException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.NombreSecret;
import com.ocr.pedsf.vue.DemandeProposition;
import com.ocr.pedsf.vue.DemandeReponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * User : class implémentant Personnage pour gérer l'utilisateur
 *
 * @author PEDSF
 * @version 1.0
 */
public class User implements Personnage {
   private static final Logger log = LogManager.getLogger(User.class);

   private String nom ;             // nom de l'utilisateur
   private MastermindProperties mp; // propriétés du jeu
   private NombreSecret ns = null;  // code secret de l'utilisateur
   private NombreSecret nsToSearch; // proposition de l'utilisateur

   public User( String nom, MastermindProperties mastermindProperties){
      this.nom = nom;
      this.mp = mastermindProperties;
      this.nsToSearch = new NombreSecret(mp.getLongueur());
   }


   public void init(){
      // saisie du code de départ par l'utilisateur
      log.info("Entrez votre nombre secret.");
      this.ns = new NombreSecret(DemandeProposition.get(mp.getLongueur(),mp.isDebugMode()));
   }

   @Override
   public String getNom() {
      return this.nom;
   }

   @Override
   public NombreSecret getNs() {
      return this.ns;
   }

   @Override
   public NombreSecret getNsToSearch() {
      return nsToSearch;
   }

   @Override
   public boolean attack(Personnage personnage) {

      if(mp.isDebugMode()) {
         log.info("{} ({}) : Proposition : ",personnage.getNom(),personnage.getNs().getNombre());
      } else {
         log.info("Proposition : ");
      }

      nsToSearch.setNombre(DemandeProposition.get(mp.getLongueur(),mp.isDebugMode()));

      if(mp.isDebugMode()) {
         log.info("{} ({}) : Proposition {} : {} ->Réponse {} : {}",
               personnage.getNom(),
               personnage.getNs().getNombre(),
               getNom(),
               nsToSearch.getNombre(),
               personnage.getNom(),
               personnage.reply(nsToSearch));
      } else {
         log.info("Proposition {} : {} -> Réponse {} : {}",
               getNom(),
               nsToSearch.getNombre(),
               personnage.getNom(),
               personnage.reply(nsToSearch));
      }

      return personnage.isEqual(nsToSearch);
   }

   @Override
   public String reply(NombreSecret nombreSecret) {

      log.info("{} ({}) : Proposition {} : {} -> Réponse {} :",
            getNom(),
            getNs().getNombre(),
            mp.getNomRobot1(),
            nombreSecret.getNombre(),
            getNom());

      String reponse = DemandeReponse.get(mp.getLongueur(),mp.isDebugMode());

      // on vérifie que l'utilisateur n'a pas fait d'erreur dans la saisie
      try {
         if (!reponse.equals(ns.test(nombreSecret))) {
            throw new MauvaiseReponseException();
         } else {
            return reponse;
         }
      } catch (CaractereIncorrectException | TailleDifferenteException | MauvaiseReponseException e) {
         log.error(e);
      }

      // rappel récursif tant que la réponse n'est pas bonne
      return reply(nombreSecret);
   }

   @Override
   public boolean isEqual(NombreSecret nombreSecret){

      return ns.equals(nombreSecret);
   }
}
