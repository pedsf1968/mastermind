package com.ocr.pedsf.controler;

import com.ocr.pedsf.exceptions.BornageException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;
import com.ocr.pedsf.model.NombreSecret;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * IA class contrôleur pour gérer l'intelkligence artificielle de l'ordinateur
 *
 * @author pedsf
 */
public class IA {
   private static final Logger log = LogManager.getLogger(IA.class);

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

   public NombreSecret getNs() {
      return ns;
   }

   public String getNombreSecret() {
      return ns.getNombre();
   }


   /**
    * proposition : méthode qui fait des propositions de combinaison de chiffre
    *               à partir de la combinaison donnée à la précédente proposition
    *               en changeant à chaque fois les bornes min et max
    *               un nouveau chiffre est calculé aléatoirement entre les bornes
    *
    * @param combinaison réponse de l'utilisateur sous forme de chaine de +-=
    */
   public void proposition(String combinaison) throws TailleDifferenteException, BornageException {
      Random rand = new Random();

     if(combinaison.length()!=this.ns.getTaille()) throw new TailleDifferenteException();


      StringBuilder sb = new StringBuilder();
      String nombre = getNs().getNombre();

      for(int i=0; i<combinaison.length();i++){
         switch(combinaison.charAt(i)){
            case '+':
               // on défini une nouvelle borne inférieure
               codeinf[i] = this.ns.getNombre().charAt(i);
               codeinf[i]++;
               if(codeinf[i]>codesup[i]) {
                  codeinf[i] = '0';
                  codesup[i] = '9';
                  throw new BornageException();
               }

               // on cherche un chiffre entre les bornes min et max
               sb.append( (int) rand.nextInt(codesup[i]-codeinf[i]+1)+codeinf[i]-'0');
               break;
            case '-':
               // on défini une nouvelle borne supérieur
               codesup[i] = this.ns.getNombre().charAt(i);
               codesup[i]--;
               if(codeinf[i]>codesup[i]) {
                  codeinf[i] = '0';
                  codesup[i] = '9';
                  throw new BornageException();
               }
               // on cherche un chiffre entre les bornes min et max
               sb.append( (int) rand.nextInt(codesup[i]-codeinf[i]+1)+codeinf[i]-'0');
               break;
            case '=':
               // c'est le bon chiffre
               sb.append(this.ns.getNombre().charAt(i));
               codeinf[i] = this.ns.getNombre().charAt(i);
               codesup[i] = this.ns.getNombre().charAt(i);
               break;
         }
      }

      this.ns = new NombreSecret( sb.toString());
   }
}
