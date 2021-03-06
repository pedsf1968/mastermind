package com.ocr.pedsf;

import com.ocr.pedsf.controler.modes.Jeu;
import com.ocr.pedsf.model.GameProperties;
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
   private static GameProperties mp;

   public static GameProperties getMp() {
      return mp;
   }

   public static void main(String[] args){
      log.traceEntry();
      // récupération de la configuration
      mp = new GameProperties( args);

      // lancement du jeu
      new Jeu();
      log.traceExit();
   }
}
