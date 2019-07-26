package com.ocr.pedsf.model.actors;

import com.ocr.pedsf.exceptions.BornageException;
import com.ocr.pedsf.exceptions.CaractereIncorrectException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.codes.NombreSecret;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * Robot : class implémentant Actor pour gérer l'IA
 *
 * @author PEDSF
 * @version 1.0
 */
public class Robot implements Actor {
   private static final Logger log = LogManager.getLogger(Robot.class);

   private String nom;                       // nom du robot
   private MastermindProperties mp;          // propriétés du jeu
   private NombreSecret ns = null;           // code secret de l'ordinateur
   private Random rand = new Random();

   // variables pour rechercher le code de l'adversaire
   private NombreSecret nsToSearch = null;   // proposition de l'ordinateur pour trouver un code
   private char[] codesup;                   // bornes inférieures
   private char[] codeinf;                   // bornes supérieures

   public Robot(String nom, MastermindProperties mastermindProperties){
      this.nom = nom;
      this.mp = mastermindProperties;
      // l'instanciation du NombreSecret est faite aléatoirement par le constructeur
      this.ns = new NombreSecret(mp.getLength());
      this.nsToSearch = new NombreSecret(mp.getLength());

      // on initialise les tableaux des bornes sup et inf
      codeinf = new char[mp.getLength()];
      codesup = new char[mp.getLength()];

      for(int i=0; i<mp.getLength(); i++){
         codeinf[i] = '0';
         codesup[i] = '9';
      }
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
   public void setNs() {
      // permet de réinitialiser le NombreSecret avec une nouvelle valeur aléatoire
      this.ns = new NombreSecret(mp.getLength());
   }

   @Override
   public NombreSecret getNsToSearch() {
      return nsToSearch;
   }

   @Override
   public boolean attack(Actor adversaire) {

      // on envoie la proposition à l'adversaire
      String combinaison = adversaire.reply(nsToSearch);

      if(mp.isDebugMode()) {
         log.info(adversaire.getNom() + " (" + adversaire.getNs().getNombre() + ") : Proposition " + getNom() + " : " + nsToSearch.getNombre() + " -> Réponse " + adversaire.getNom() + " : " + combinaison);
      } else {
         log.info("Proposition " + getNom() + " : " + nsToSearch.getNombre() + " -> Réponse " + adversaire.getNom() + " : " + combinaison);
      }

      // on sort de la boucle si c'est la bonne réponse
      if(adversaire.isEqual(nsToSearch)) return true;

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
                  sb.append( rand.nextInt(codesup[i] - codeinf[i] + 1) + codeinf[i] - '0');
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
                  sb.append( rand.nextInt(codesup[i] - codeinf[i] + 1) + codeinf[i] - '0');
                  break;
               case '=':
                  // c'est le bon chiffre
                  sb.append(nsToSearch.getNombre().charAt(i));
                  codeinf[i] = nsToSearch.getNombre().charAt(i);
                  codesup[i] = nsToSearch.getNombre().charAt(i);
                  break;
               default:
                  throw new CaractereIncorrectException();
            }
         }
      } catch (BornageException | CaractereIncorrectException e){
         log.error(e);
      }

      // on affecte la nouvelle valeur
      this.nsToSearch = new NombreSecret( sb.toString());

      return false;
   }

   @Override
   public String reply(NombreSecret nombreSecret) {
      // appel de la méthode interne de NombreSecret pour avoir la réponse
      try {
         return ns.test(nombreSecret);
      } catch (TailleDifferenteException | CaractereIncorrectException e) {
         log.error(e);
      }

      return null;
   }

   @Override
   public boolean isEqual(NombreSecret nombreSecret){
      return ns.equals(nombreSecret);
   }

}
