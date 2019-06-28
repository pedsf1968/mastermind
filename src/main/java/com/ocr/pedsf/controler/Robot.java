package com.ocr.pedsf.controler;

import com.ocr.pedsf.exceptions.BornageException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.NombreSecret;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Robot implements Personnage {
   private static final Logger log = LogManager.getLogger(Robot.class);

   private String nom = "";
   private MastermindProperties mp = null;
   private NombreSecret ns = null; // code secret de l'ordinateur
   private NombreSecret nsToSearch = null; // proposition de l'ordinateur pour trouver un code
   private char[] codesup; // bornes inférieures
   private char[] codeinf; // bornes supérieures

   public Robot(String nom, MastermindProperties mastermindProperties){
      this.nom = nom;
      this.mp = mastermindProperties;

      // on initialise les tableaux des bornes sup et inf
      codeinf = new char[mp.getLongueur()];
      codesup = new char[mp.getLongueur()];
      for(int i=0; i<mp.getLongueur(); i++){
         codeinf[i] = '0';
         codesup[i] = '9';
      }
   }

   @Override
   public void init() {
      // l'instanciation du NombreSecret est faite aléatoirement par le constructeur
      this.ns = initNombreSecret();
      this.nsToSearch = initNombreSecret();
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
      // on envoie la proposition à l'adversaire
      String combinaison = personnage.reply(nsToSearch);

      System.out.println("Proposition " + getNom() + " : " + nsToSearch.getNombre() + " -> Réponse " + personnage.getNom() + " : " + combinaison);

      // on sort de la boucle si c'est la bonne réponse
      if(personnage.isEqual(nsToSearch)) return true;

      // on fait une nouvelle estimation pour la prochaine fois
      Random rand = new Random();

      if(combinaison.length()!=this.nsToSearch.getTaille()) try {
         throw new TailleDifferenteException();
      } catch (TailleDifferenteException e) {
         log.error(e);
      }

      StringBuilder sb = new StringBuilder();

      try {
         for (int i = 0; i < combinaison.length(); i++) {
            switch (combinaison.charAt(i)) {
               case '+':
                  // on défini une nouvelle borne inférieure
                  codeinf[i] = nsToSearch.getNombre().charAt(i);
                  codeinf[i]++;
                  if (codeinf[i] > codesup[i]) {
                     codeinf[i] = '0';
                     codesup[i] = '9';
                     throw new BornageException();
                  }
                  // on cherche un chiffre entre les bornes min et max
                  sb.append((int) rand.nextInt(codesup[i] - codeinf[i] + 1) + codeinf[i] - '0');
                  break;
               case '-':
                  // on défini une nouvelle borne supérieur
                  codesup[i] = nsToSearch.getNombre().charAt(i);
                  codesup[i]--;
                  if (codeinf[i] > codesup[i]) {
                     codeinf[i] = '0';
                     codesup[i] = '9';
                     throw new BornageException();
                  }
                  // on cherche un chiffre entre les bornes min et max
                  sb.append((int) rand.nextInt(codesup[i] - codeinf[i] + 1) + codeinf[i] - '0');
                  break;
               case '=':
                  // c'est le bon chiffre
                  sb.append(nsToSearch.getNombre().charAt(i));
                  codeinf[i] = nsToSearch.getNombre().charAt(i);
                  codesup[i] = nsToSearch.getNombre().charAt(i);
                  break;
            }
         }
      } catch (BornageException e){
         log.error(e);
      }

      // on affecte la nouvelle valeur
      this.nsToSearch = new NombreSecret( sb.toString());

      return false;
   }

   @Override
   public String reply(NombreSecret nombreSecret) {
      // appel de la méthode interne de NombreSecret pour avoir la réponse
      return ns.test(nombreSecret);

   }

   @Override
   public boolean isEqual(NombreSecret nombreSecret){
      return ns.equals(nombreSecret);
   }


   /**
    * initNombreSecret : initialise un nombreSecret avec une valeur aléatoire
    * @return
    */
   NombreSecret initNombreSecret(){
      StringBuilder sb = new StringBuilder();

      for(int i=0; i<mp.getLongueur(); i++) {
         sb.append(Math.round(Math.floor( (Math.random() * 10.0))));
      }

      return new NombreSecret(sb.toString());
   }
}
