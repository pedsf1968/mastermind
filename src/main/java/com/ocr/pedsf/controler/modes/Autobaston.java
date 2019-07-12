package com.ocr.pedsf.controler.modes;

import com.ocr.pedsf.controler.Mode;
import com.ocr.pedsf.model.MastermindProperties;
import com.ocr.pedsf.model.Personnage;
import com.ocr.pedsf.model.personnages.Robot;
import com.ocr.pedsf.vue.Resultat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Autobaston implements Mode {
   private static final Logger log = LogManager.getLogger(Duel.class);

   private MastermindProperties mp;
   private boolean trouve = false;
   private int nbCoup = 0;

   public Autobaston(MastermindProperties mp){
      this.mp = mp;
   }

   public void setMp(MastermindProperties mp) {
      this.mp = mp;
   }

   @Override
   public void run() {
      log.traceEntry();
      System.out.println("\nMASTERMIND : Mode AutoBaston\n");

      // initialisation des protagonistes
      Personnage chapi = new Robot(mp.getNomRobot1(),mp);
      Personnage chapo = new Robot(mp.getNomRobot2(),mp);

      // initialisation des NombresSecrets
      chapi.init();
      chapo.init();

      if (mp.isDebugMode()) {
         System.out.println("(Combinaison secrète " + chapi.getNom() + " : " + chapi.getNs().getNombre() + ")\n");
         System.out.println("(Combinaison secrète " + chapo.getNom() + " : " + chapo.getNs().getNombre() + ")\n");
      }

      do {
         // Chapi attaque Chapo
         trouve = chapi.attack(chapo);

         if(trouve) {
            Resultat.display(chapi.getNom(), chapo.getNom(), nbCoup, chapo.getNs().getNombre());
         } else {
            // au tour de Chapo
            trouve = chapo.attack(chapi);
            if(trouve)
               Resultat.display(chapo.getNom(), chapi.getNom(), nbCoup, chapi.getNs().getNombre());
         }

         nbCoup++;
      } while (!trouve);

      log.traceExit();
   }
}
