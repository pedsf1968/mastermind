package com.ocr.pedsf.model.codes;

/**
 * ActorFactory : factory pour instancier les class de Code
 *
 * SimplifiedCode : implement a simplified Code search
 * MastermindCode : implement the Code for Mastermind type
 *
 * @author pedsf
 * @version 1.0
 */
public class CodeFactory {
   public static final int CODE_SIMPLIFIED = 1;
   public static final int CODE_MASTERMIND = 2;

   private CodeFactory(){
      throw new IllegalStateException("Utility Class");
   }

   /**
    * get : method that return the required Code instance
    *
    * @param codeType indication of the type of code generate
    * @param length of the code
    * @return the Code instance
    */
   public static Code get(int codeType, int length){
      switch (codeType){
         case CODE_SIMPLIFIED:
            return new SimplifiedCode(length);
         case CODE_MASTERMIND:
            return new MastermindCode(length);
         default:
            return new SimplifiedCode(length);
      }
   }

   /**
    * get : method that return the required Code instance
    *
    * @param codeType indication of the type of code generate
    * @param code to be used
    * @return the Code instance
    */
   public static Code get(int codeType, String code){
      switch (codeType){
         case CODE_SIMPLIFIED:
            return new SimplifiedCode(code);
         case CODE_MASTERMIND:
            return new MastermindCode(code);
         default:
            return new SimplifiedCode(code);
      }
   }


}
