package com.ocr.pedsf.controler;

import com.ocr.pedsf.exceptions.MauvaiseReponseException;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.NombreSecret;
import com.ocr.pedsf.vue.DemandeProposition;
import com.ocr.pedsf.vue.DemandeReponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class User implements Personnage{
   private static final Logger log = LogManager.getLogger(User.class);

   private String nom = "";
   private MastermindProperties mp = null;
   private NombreSecret ns = null; // code secret de l'utilisateur
   private NombreSecret nsToSearch = null; // proposition de l'utilisateur

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
      if (mp.isModeDeveloppeur())
         return ns;
      else
         return null;
   }

   @Override
   public NombreSecret getNsToSearch() {
      return nsToSearch;
   }

   @Override
   public boolean attack(Personnage personnage) {

      if(mp.isModeDeveloppeur()) {
         System.out.print("Proposition (" + personnage.getNs().getNombre() + ") : ");
      } else {
         System.out.print("Proposition : ");
      }

      nsToSearch.setNombre(DemandeProposition.get(mp.getLongueur()));

      System.out.println("Proposition "+ getNom() + " : " + nsToSearch.getNombre() + " -> Réponse " + personnage.getNom() + " : " + personnage.reply(nsToSearch));


      if (personnage.isEqual(nsToSearch)) return true;

      return false;
   }

   @Override
   public String reply(NombreSecret nombreSecret) {

      System.out.print("Votre code : " + ns.getNombre() + " proposition de l'adversaire : " + nombreSecret.getNombre() + " réponse : ");
      String reponse = DemandeReponse.get(mp.getLongueur(),mp.isModeDeveloppeur());

      // on vérifie que l'utilisateur n'a pas fait d'erreur dans la saisie
      try {
         if (!reponse.equals(ns.test(nombreSecret))) {
            throw new MauvaiseReponseException();
         } else {
            return reponse;
         }
      } catch (MauvaiseReponseException e) {
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
