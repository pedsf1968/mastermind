package com.ocr.pedsf.controler;

import com.ocr.pedsf.exceptions.BornageException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.NombreSecret;
import com.ocr.pedsf.vue.Resultat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Autobaston class contrôleur pour le mode AutoBaston
 *
 * @author pedsf
 */
public class AutoBaston implements Mode {
   private static final Logger log = LogManager.getLogger(AutoBaston.class);

   private MastermindProperties mp;
   private boolean trouve = false;
   private int nbCoup = 1;
   private String proposition1 = "";
   private String proposition2 = "";
   private String reponse1 = "";
   private String reponse2 = "";

   public AutoBaston(MastermindProperties mp){
      this.mp = mp;
   }

   /**
    * run : méthode qui lance l'AutoBaston
    */
   public void run(){
      log.debug("Lancement du mode AutoBaston");
      System.out.println("\nMASTERMIND : Mode AutoBaston\n");

      // initialisation des protagonistes
      Personnage ia1 = new Robot("IA 1",mp);
      Personnage ia2 = new Robot("IA 2",mp);

      // initialisation des NombresSecrets
      ia1.init();
      ia2.init();


      do {
         // l'utilisateur attaque l'ordinateur
         trouve = ia1.attack(ia2);

         // au tour de l'ordinateur si la réponse n'est pas bonne
         if(!trouve)
            trouve = ia2.attack(ia1);
         nbCoup++;
      } while (!trouve);

      if (ia2.getNs().equals(ia1.getNsToSearch())) {
         Resultat.display(ia1.getNom(),ia2.getNom(), nbCoup, ia1.getNs().getNombre());
         log.debug(ia1.getNom(),ia2.getNom(), nbCoup, ia1.getNs().getNombre());
      } else {
         Resultat.display(ia2.getNom(),ia1.getNom(), nbCoup, ia2.getNs().getNombre());
         log.debug(ia2.getNom(),ia1.getNom(), nbCoup, ia2.getNs().getNombre());
      }
   }
}
