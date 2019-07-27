package com.ocr.pedsf.model.codes;

import com.ocr.pedsf.exceptions.CaractereIncorrectException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * MastermindCode : class pfor Mastermind type code
 *
 * @author pedsf
 */
public class MastermindCode implements Code{
   private static final Logger log = LogManager.getLogger(MastermindCode.class);
   protected String nombre = "";
   protected int taille;

   public static final String SHORTMESSAGE = "\nIndiquez pour chaque chiffre (=) bien placé, (+) existant ou (-) absent";
   public static final String LONGMESSAGE = "\nIndiquez pour chaque chiffre de la combinaison proposée si" +
                                       " le chiffre de sa combinaison est :\n" +
                                       "mal placé par un (+), inexistant par un (-) ou bien placé par un (=)";

   private List<Character>[] chiffrePossible ;
   private List<Character> chiffreMalPlace;

   private Random rand;

   public MastermindCode(String nombre) {
      this.nombre = nombre;
      this.taille = nombre.length();
   }

   public MastermindCode(int taille) {
      this.taille = taille;
      init(false);
   }

   @Override
   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
      this.taille = nombre.length();
   }

   @Override
   public int getTaille() {
      return taille;
   }

   public void setTaille(int taille) {
      this.taille = taille;
      init(false);
   }

   @Override
   public void init(boolean isSearching){
      StringBuilder sb = new StringBuilder();

      for(int i=0; i<this.taille; i++) {
         sb.append(Math.round(Math.floor( (Math.random() * 10.0))));
      }

      this.nombre = sb.toString();

      // initialisation des listes de recherche
      if(isSearching){
         chiffrePossible = new ArrayList[this.taille];
         chiffreMalPlace = new ArrayList<>();
         // on initialise le tableau des chiffres présents
         for(int i=0;i<this.taille;i++) {
            chiffrePossible[i] = new ArrayList<>();
            for (char c = '0'; c <= '9'; c++)
               chiffrePossible[i].add(c);
         }
      }

   }

   @Override
   public String toString() {
      return "MastermindCode{" +
            "nombre='" + nombre + '\'' +
            ", taille=" + taille +
            '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof MastermindCode)) return false;
      MastermindCode that = (MastermindCode) o;

      if (this.taille != that.taille) return false;

      for( int i=0; i<this.taille; i++)
         if(this.nombre.charAt(i)!=that.nombre.charAt(i)) return false;

      return true;

   }

   @Override
   public int hashCode() {
      int result = Objects.hash(getNombre(), getTaille(), chiffreMalPlace, rand);
      result = 31 * result + Arrays.hashCode(chiffrePossible);
      return result;
   }

   /**
    * test : compare le MastermindCode avec un autre
    *
    * @param code à comparer
    * @return la chaine de caractères montrant les différences
    * @throws TailleDifferenteException si les deux nombres sont de taille différentes
    * @throws CaractereIncorrectException si un nombre ne comporte pas que des chiffres
    */
   public String test(Code code) throws TailleDifferenteException, CaractereIncorrectException {
      MastermindCode mastermindCode = (MastermindCode) code;
      return test(mastermindCode.getNombre());
   }

   /**
    * test : compare le MastermindCode avec une chaine de chiffre et retourne la vrai réponse mastermind
    *        - chiffre absent
    *        + chiffre présent mal placé
    *        = chiffre présent bien placé
    *
    * @param chaine à comparer
    * @return la chaine de caractères montrant les différences
    * @throws TailleDifferenteException si les deux nombres sont de taille différentes
    * @throws CaractereIncorrectException si un nombre ne comporte pas que des chiffres
    */
   public String test(String chaine) throws TailleDifferenteException, CaractereIncorrectException {
      StringBuilder sb = new StringBuilder();

      // on ne peut pas comparer des nombres différents
      if(chaine.length()!=this.getTaille()) throw new TailleDifferenteException();

      for(int i=0; i<this.getTaille(); i++) {
         char c =chaine.charAt(i);

         // on teste si la chaine contient uniquement des chiffres
         if ( c< '0' || c > '9') throw new CaractereIncorrectException();

         if(c==this.getNombre().charAt(i))
            sb.append('=');
         else if (this.getNombre().contains(String.valueOf(c)))
            sb.append('+');
         else
            sb.append('-');
      }

      return log.traceExit(sb.toString());
   }


   public void evaluateNext(String combinaison){
   StringBuilder sb = new StringBuilder();

      for (int i = 0; i < combinaison.length(); i++) {
         char chiffreCourantPropose = this.nombre.charAt(i);

         switch (combinaison.charAt(i)) {
            case '+':
               // le chiffre est mal placé
               // on l'ajoute à chiffreMalPlace
               if(chiffreMalPlace!=null && !chiffreMalPlace.contains(chiffreCourantPropose))
                     chiffreMalPlace.add(chiffreCourantPropose);

               // on le supprime de la liste des chiffrePossible du chiffre
               chiffrePossible[i].remove((Character)chiffreCourantPropose);

               sb.append(proposeAutreChar(chiffreCourantPropose, chiffreMalPlace, chiffrePossible[i]));
               break;
            case '-':
               // le chiffre n'est pas présent dans le code, on le supprime de toutes les listes chiffrePossible
               for(int j=0;j<combinaison.length();j++){
                  if(chiffrePossible[j].contains(chiffreCourantPropose)) {
                     chiffrePossible[j].remove((Character)chiffreCourantPropose);
                  }
               }
               sb.append(proposeAutreChar(chiffreCourantPropose, chiffreMalPlace, chiffrePossible[i]));
               break;
            case '=':
               // c'est le bon chiffre
               sb.append(chiffreCourantPropose);
               break;
            default:
         }
      }

      this.nombre = sb.toString();
   }

   /**
    * proposeAutreChar : propose un nouveau caractère autre que le caractère courant parmis les caractères prioritaires
    *                    si ils sont présent parmis les caractères possibles sinon un caractère possible.
    *
    * @param courant caractère interdit
    * @param charPrioritaire première chaînes pour chercher les possibilités
    * @param charPossible seconde chaînes pour les possibilités
    * @return un caractère disponible dans l'une des chaînes
    */
   public static char proposeAutreChar(char courant, List<Character> charPrioritaire, List<Character> charPossible){

      // on cherche dans la chaine prioritaire
      if(charPrioritaire!=null)
         for (char c : charPrioritaire) {
            // si le caractère n'est pas le caractère courant et si il est dans les caractères possible on le renvoie
            if (c != courant && charPossible.contains(c)) return c;
         }

      // on cherche un caractère dans l'autre chaine
      for (char c : charPossible) {
         // si dans la chaine des possible il y a des caractères on renvoie le premier différent de courant
         if (c != courant) return c;
      }

      return courant;
   }

}

