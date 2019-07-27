package com.ocr.pedsf.vue;

import com.ocr.pedsf.Main;
import com.ocr.pedsf.model.GameProperties;
import com.ocr.pedsf.model.codes.CodeFactory;
import com.ocr.pedsf.model.codes.MastermindCode;
import com.ocr.pedsf.model.codes.SimplifiedCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * AskAnswer : ask the answer of a proposition
 *               the get() method use
 *               display() to diplay the message
 *               and ask() to get right  answer
 *
 * @author pedsf
 * @version 1.0
 */
public class AskAnswer extends Ask {
   private static final Logger log = LogManager.getLogger(AskAnswer.class.getName());
   private static GameProperties properties;

   public static void setMp(GameProperties properties) {
      AskAnswer.properties = properties;
   }

   /**
    * get : méthode générale pour demander une réponse à la proposition de l'adversaire
    *       elle pause la question avec display()
    *       et demande à l'utilisateur avec ask() jusqu'à ce que la réponse soit correcte
    *
    * @param digit taille du code à trouver
    * @param isDebugMode activation du mode debug
    * @return réponse saisie par l'utilisateur
    */
   public static String get(int digit, boolean isDebugMode){
      log.traceEntry();
      if(Main.getMp()!=null)
         properties = Main.getMp();

      String message;
      String errorMessage;
      String pattern = "[-+=]{" + digit + "}";

      if(properties.getGameType()== CodeFactory.CODE_SIMPLIFIED){
         message = SimplifiedCode.SHORTMESSAGE;
         errorMessage = "Mauvaise saisie !\n"+ SimplifiedCode.LONGMESSAGE;
      } else {
         message = MastermindCode.SHORTMESSAGE;
         errorMessage = "Mauvaise saisie !\n" + MastermindCode.LONGMESSAGE;
      }

      return log.traceExit(getString(message,  errorMessage,pattern,isDebugMode));
   }

}
