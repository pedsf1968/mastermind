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
public class AutoBaston {
   private static final Logger log = LogManager.getLogger(AutoBaston.class);

   private MastermindProperties mp;
   private boolean trouve;
   private int nbCoup;
   private String proposition1;
   private String proposition2;
   private String reponse1;
   private String reponse2;

   public AutoBaston(MastermindProperties mp){
      this.mp = mp;

      this.trouve = false;
      this.nbCoup = 1;
      this.proposition1 = "";
      this.proposition2 = "";
      this.reponse1 = "";
      this.reponse2 = "";
   }

   public void run(){
      log.debug("Lancement du mode AutoBaston");
      System.out.println("\nMASTERMIND : Mode AutoBaston\n");

      // initialisation des nombres secrets des IA
      NombreSecret nso1 = new NombreSecret(mp.getLongueur());
      NombreSecret nso2 = new NombreSecret(mp.getLongueur());

      if(mp.isModeDeveloppeur())
         System.out.println("(Combinaison secrète 1: " + nso1.getNombre() + " secrète 2: " + nso2.getNombre() + ")");

      // on initialise les IA avec leur proposition aléatoire de départ
      IA ia1 = new IA(mp.getLongueur());
      IA ia2 = new IA(mp.getLongueur());

      do {
         // on récupère la proposition de IA1
         proposition1 = ia1.getNombreSecret();

         try {
            // IA1 fait une proposition à IA2
            reponse2 = nso2.test(proposition1);
            System.out.println("Code IA2 : " + nso2.getNombre() + " proposition IA1 : " + proposition1 + " réponse IA2 : " +reponse2);

            if(proposition1.equals(nso2.getNombre())) {
               trouve = true;
               Resultat.display("IA1", "IA2",nbCoup,nso2.getNombre());
               log.debug("IA1", "IA2",nbCoup,nso2.getNombre());
            } else {
               // recalcul suivant la réponse de IA2 pour le prochain tour
               ia1.proposition(reponse2);

               // si la réponse n'est pas trouvée c'est au tour de IA 2
               // on récupère la proposition de IA2
               proposition2 = ia2.getNombreSecret();

               // IA2 fait une proposition à IA1
               reponse1 = nso1.test(proposition2);
               System.out.println("Code IA1 : " + nso1.getNombre() + " proposition IA2 : " + proposition2 + " réponse IA1 : " + reponse1);

               if (proposition2.equals(nso1.getNombre())) {
                  trouve = true;
                  Resultat.display("IA2", "IA1", nbCoup, nso1.getNombre());
                  log.debug("IA2", "IA1", nbCoup, nso1.getNombre());
               } else {
                  // recalcul suivant la réponse de IA1
                  ia2.proposition(reponse1);
               }
            }
         } catch (TailleDifferenteException | BornageException e) {
            e.printStackTrace();
         }

         // on incrémente le conteur de tour
         nbCoup++;

      } while (!this.trouve);

   }
}
