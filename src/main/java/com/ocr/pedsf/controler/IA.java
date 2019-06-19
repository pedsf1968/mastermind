package com.ocr.pedsf.controler;

import com.ocr.pedsf.model.NombreSecret;

public class IA {
   private NombreSecret ns;

   public IA(int digit){
      ns = new NombreSecret(digit);
   }

   public IA(String code){
      ns = new NombreSecret(code);
   }

   public String getNombreSecret() {
      return ns.getNombre();
   }

   public void proposition(String combinaison){
      StringBuilder sb = new StringBuilder();
      String nombre = getNombreSecret();

      for(int i=0; i<combinaison.length();i++){
         // on enlève le code ASCII de zéro pour obtenir un entier
         int valeur = this.ns.getNombre().charAt(i)-'0';

         switch(combinaison.charAt(i)){
            case '+':
               // on cherche un chiffre entre valeur et 9
               sb.append( (int) Math.round(Math.floor( (valeur + Math.random()*(9-valeur)+1))));
               break;
            case '-':
               // on cherche un chiffre entre 0 et valeur
               sb.append( (int) Math.round(Math.floor( (Math.random()*valeur))));
               break;
            case '=':
               // c'est le bon chiffre
               sb.append(valeur);
               break;
         }
      }

      this.ns = new NombreSecret( sb.toString());
   }
}
