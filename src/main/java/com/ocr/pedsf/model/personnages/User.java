package com.ocr.pedsf.model.personnages;

import com.ocr.pedsf.exceptions.CaractereIncorrectException;
import com.ocr.pedsf.exceptions.MauvaiseReponseException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.NombreSecret;
import com.ocr.pedsf.model.Personnage;
import com.ocr.pedsf.vue.DemandeProposition;
import com.ocr.pedsf.vue.DemandeReponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class User implements Personnage {
   private static final Logger log = LogManager.getLogger(User.class);

   private String nom ;
   private MastermindProperties mp;
   private NombreSecret ns = null; // code secret de l'utilisateur
   private NombreSecret nsToSearch; // proposition de l'utilisateur

   public User( String nom, MastermindProperties mastermindProperties){
      this.nom = nom;
      this.mp = mastermindProperties;
      this.nsToSearch = new NombreSecret(mp.getLongueur());
   }

   public void init(){
      // saisie du code de départ par l'utilisateur
      if(mp.getLongueur()==1) {
         System.out.print("Entrez votre nombre secret de 1 chiffre : ");
      } else {
         System.out.print("Entrez votre nombre secret de " + mp.getLongueur() + " chiffres : ");
      }

      this.ns = new NombreSecret(DemandeProposition.get(mp.getLongueur()));
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
         System.out.print(personnage.getNom() + " (" + personnage.getNs().getNombre() + ") : Proposition : ");
      } else {
         System.out.print("Proposition : ");
      }

      nsToSearch.setNombre(DemandeProposition.get(mp.getLongueur()));

      if(mp.isDebugMode()) {
         System.out.println(personnage.getNom() + " (" + personnage.getNs().getNombre() + ") : Proposition " + getNom() + " : " + nsToSearch.getNombre() + " -> Réponse " + personnage.getNom() + " : " + personnage.reply(nsToSearch));
      } else {
         System.out.println("Proposition "+ getNom() + " : " + nsToSearch.getNombre() + " -> Réponse " + personnage.getNom() + " : " + personnage.reply(nsToSearch));
      }

      return personnage.isEqual(nsToSearch);
   }

   @Override
   public String reply(NombreSecret nombreSecret) {

      System.out.print(getNom() + " (" + getNs().getNombre() + ") : Proposition " + mp.getNomRobot1() + " : " + nombreSecret.getNombre() + " -> Réponse " + getNom() + " : ");

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
