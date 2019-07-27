package com.ocr.pedsf.model.actors;

import com.ocr.pedsf.exceptions.CaractereIncorrectException;
import com.ocr.pedsf.exceptions.MauvaiseReponseException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.GameProperties;
import com.ocr.pedsf.model.codes.Code;
import com.ocr.pedsf.model.codes.CodeFactory;
import com.ocr.pedsf.vue.AskProposition;
import com.ocr.pedsf.vue.AskAnswer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * UserActor : class implémentant Actor pour gérer l'utilisateur
 *
 * @author PEDSF
 * @version 1.0
 */
public class UserActor implements Actor {
   private static final Logger log = LogManager.getLogger(UserActor.class);

   private String nom ;             // nom de l'utilisateur
   private GameProperties mp; // propriétés du jeu
   private Code ns;                 // code secret de l'utilisateur
   private Code nsToSearch;         // proposition de l'utilisateur

   public UserActor(String nom, GameProperties mastermindProperties){
      this.nom = nom;
      this.mp = mastermindProperties;
      this.nsToSearch = CodeFactory.get(mp.getGameType(),mp.getLength());
   }

   @Override
   public void setNs() {
      // saisie du code de départ par l'utilisateur
      log.info("Entrez votre nombre secret.");

      this.ns = CodeFactory.get(mp.getGameType(),AskProposition.get(mp.getLength(),mp.isDebugMode()));
   }

   @Override
   public String getNom() {
      return this.nom;
   }

   @Override
   public Code getNs() {
      return this.ns;
   }

   @Override
   public Code getNsToSearch() {
      return nsToSearch;
   }

   @Override
   public boolean attack(Actor adversaire) {

      if(mp.isDebugMode()) {
         log.info("{} ({}) : Proposition : ",adversaire.getNom(),adversaire.getNs().getNombre());
      } else {
         log.info("Proposition : ");
      }

      nsToSearch.setNombre(AskProposition.get(mp.getLength(),mp.isDebugMode()));

      if(mp.isDebugMode()) {
         log.info("{} ({}) : Proposition {} : {} ->Réponse {} : {}",
               adversaire.getNom(),
               adversaire.getNs().getNombre(),
               getNom(),
               nsToSearch.getNombre(),
               adversaire.getNom(),
               adversaire.reply(nsToSearch));
      } else {
         log.info("Proposition {} : {} -> Réponse {} : {}",
               getNom(),
               nsToSearch.getNombre(),
               adversaire.getNom(),
               adversaire.reply(nsToSearch));
      }

      return adversaire.isEqual(nsToSearch);
   }

   @Override
   public String reply(Code code) {

      log.info("{} ({}) : Proposition {} : {} -> Réponse {} :",
            getNom(),
            getNs().getNombre(),
            mp.getRobot1Name(),
            code.getNombre(),
            getNom());

      String reponse = AskAnswer.get(mp.getLength(),mp.isDebugMode());

      // on vérifie que l'utilisateur n'a pas fait d'erreur dans la saisie
      try {
         if (!reponse.equals(ns.test(code))) {
            throw new MauvaiseReponseException();
         } else {
            return reponse;
         }
      } catch (CaractereIncorrectException | TailleDifferenteException | MauvaiseReponseException e) {
         log.error(e);
      }

      // rappel récursif tant que la réponse n'est pas bonne
      return reply(code);
   }

   @Override
   public boolean isEqual(Code code){
         return ns.equals(code);

   }
}
