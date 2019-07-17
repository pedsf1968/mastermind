package com.ocr.pedsf;

import com.ocr.pedsf.controler.modes.Jeu;
import com.ocr.pedsf.model.MastermindProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main : class principale de lancement du jeu
 *
 * @author pedsf
 * @version 1.0
 */
public class Main {
   private static final Logger log = LogManager.getLogger(Main.class);

   public static void main(String[] args){
      // récupération de la configuration
      MastermindProperties mp = new MastermindProperties( args);

      // lancement du jeu
      new Jeu(mp);
   }
}
