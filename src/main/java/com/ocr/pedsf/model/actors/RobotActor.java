package com.ocr.pedsf.model.actors;

import com.ocr.pedsf.exceptions.CaractereIncorrectException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.GameProperties;
import com.ocr.pedsf.model.codes.Code;
import com.ocr.pedsf.model.codes.CodeFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * RobotActor : class implémentant Actor pour gérer l'IA
 *
 * @author PEDSF
 * @version 1.0
 */
public class RobotActor implements Actor {
   private static final Logger log = LogManager.getLogger(RobotActor.class);

   private String nom;                // nom du robot
   private GameProperties mp;   // propriétés du jeu
   private Code ns;           // code secret de l'ordinateur

   // variables pour rechercher le code de l'adversaire
   private Code nsToSearch;   // proposition de l'ordinateur pour trouver un code


   public RobotActor(String nom, GameProperties mastermindProperties){
      this.nom = nom;
      this.mp = mastermindProperties;

      // l'instanciation du NombreSecret est faite aléatoirement par le constructeur
      this.ns = CodeFactory.get(mp.getGameType(),mp.getLength());
      this.nsToSearch = CodeFactory.get(mp.getGameType(),mp.getLength());

      //active le mode recherche pour le second code
      this.nsToSearch.init(true);
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
   public void setNs() {
      // permet de réinitialiser le NombreSecret avec une nouvelle valeur aléatoire
      this.ns.init(false);
   }

   @Override
   public Code getNsToSearch() {
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

      // recherche nouvelle combinaison
      nsToSearch.evaluateNext(combinaison);

      return false;
   }

   @Override
   public String reply(Code code) {
      // appel de la méthode interne de NombreSecret pour avoir la réponse
      try {
         return ns.test(code);
      } catch (TailleDifferenteException | CaractereIncorrectException e) {
         log.error(e);
      }

      return null;
   }

   @Override
   public boolean isEqual(Code code){
      return ns.equals(code);
   }

}
