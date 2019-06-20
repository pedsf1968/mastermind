package com.ocr.pedsf.controler;

import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.NombreSecret;

public class IA {
   private NombreSecret ns; // proposition de l'ordinateur pour trouver un code
   private char[] codesup; // bornes inférieures
   private char[] codeinf; // bornes supérieures

   public IA(int digit){
      // l'instanciation du NombreSecret est faite aléatoirement par le constructeur
      ns = new NombreSecret(digit);
      // on initialise les tableaux des bornes sup et inf
      codeinf = new char[digit];
      codesup = new char[digit];
      for(int i=0; i<digit; i++){
         codeinf[i] = '0';
         codesup[i] = '9';
      }
   }

   public IA(String code){
      ns = new NombreSecret(code);
      // on initialise les tableaux des bornes sup et inf
      codeinf = new char[code.length()];
      codesup = new char[code.length()];
      for(int i=0; i<code.length(); i++){
         codeinf[i] = '0';
         codesup[i] = '9';
      }
   }

   public String getNombreSecret() {
      return ns.getNombre();
   }

   /**
    * proposition : méthode qui fait des propositions de combinaison de chiffre
    *               en changeant à chaque fois les bornes min et max
    *               un nouveau chiffre est calculé aléatoirement entre les bornes
    *
    * @param combinaison réponse de l'utilisateur sous forme de chaine de +-=
    */
   public void proposition(String combinaison) throws TailleDifferenteException {

     if(combinaison.length()!=this.ns.getTaille()) throw new TailleDifferenteException();


      StringBuilder sb = new StringBuilder();
      String nombre = getNombreSecret();

      for(int i=0; i<combinaison.length();i++){
         // on enlève le code ASCII de zéro pour obtenir un entier
         int valeur = this.ns.getNombre().charAt(i)-'0';

         switch(combinaison.charAt(i)){
            case '+':
               // on défini une nouvelle borne inférieure
               codeinf[i] = this.ns.getNombre().charAt(i);
               // on cherche un chiffre entre les bornes min et max
               sb.append( (int) Math.round(Math.floor( (codeinf[i]-'0' + Math.random()*(codesup[i]-codeinf[i])+1))));
               break;
            case '-':
               // on défini une nouvelle borne supérieur
               codesup[i] = this.ns.getNombre().charAt(i);
               // on cherche un chiffre entre les bornes min et max
               sb.append( (int) Math.round(Math.floor( (codeinf[i]-'0' + Math.random()*(codesup[i]-codeinf[i])))));
               break;
            case '=':
               // c'est le bon chiffre
               sb.append(valeur);
               codeinf[i] = this.ns.getNombre().charAt(i);
               codesup[i] = this.ns.getNombre().charAt(i);
               break;
         }
      }

      this.ns = new NombreSecret( sb.toString());
   }
}
