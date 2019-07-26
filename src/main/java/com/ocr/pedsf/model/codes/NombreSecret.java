package com.ocr.pedsf.model.codes;

import com.ocr.pedsf.exceptions.BornageException;
import com.ocr.pedsf.exceptions.CaractereIncorrectException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Random;

/**
 * NombreSecret : class pour gérer les chaines de nombres
 *
 * @author pedsf
 * @version 1.0
 */
public class NombreSecret implements Code {
   private static final Logger log = LogManager.getLogger(NombreSecret.class);
   private String nombre = "";
   private int taille;
   private char[] codesup;                   // bornes inférieures
   private char[] codeinf;                   // bornes supérieures

   private Random rand;

   public NombreSecret(String nombre) {
      this.nombre = nombre;
      this.taille = nombre.length();
   }

   public NombreSecret(int taille) {
      this.taille = taille;
      init(false);
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
      this.taille = nombre.length();
   }

   public int getTaille() {
      return taille;
   }

   public void setTaille(int taille) {
      this.taille = taille;
      init(false);
   }

   /**
    * init : méthode d'initialisation avec un nombre aléatoire
    */
   public void init(boolean isSearching){
      StringBuilder sb = new StringBuilder();

      for(int i=0; i<this.taille; i++) {
         sb.append(Math.round(Math.floor( (Math.random() * 10.0))));
      }

      this.nombre = sb.toString();

      if(isSearching) {
         rand = new Random();
         // on initialise les tableaux des bornes sup et inf
         codeinf = new char[this.taille];
         codesup = new char[this.taille];

         for (int i = 0; i < this.taille; i++) {
            codeinf[i] = '0';
            codesup[i] = '9';
         }
      }
   }
   @Override
   public String toString() {
      return "NombreSecret{" +
            "nombre='" + nombre + '\'' +
            ", taille=" + taille +
            '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      NombreSecret that = (NombreSecret) o;

      if (this.taille != that.taille) return false;

      for( int i=0; i<this.taille; i++)
         if(this.nombre.charAt(i)!=that.nombre.charAt(i)) return false;

      return true;
   }

   @Override
   public int hashCode() {
      return Objects.hash(nombre, taille);
   }

   /**
    * test : compare le NombreSecret avec un autre
    *
    * @param code à comparer
    * @return la chaine de caractères montrant les différences
    * @throws TailleDifferenteException si les deux nombres sont de taille différentes
    * @throws CaractereIncorrectException si un nombre ne comporte pas que des chiffres
    */
   public String test(Code code) throws TailleDifferenteException, CaractereIncorrectException {
      NombreSecret nombreSecret = (NombreSecret) code;

     if(nombreSecret.getTaille()!=this.taille)
        throw new TailleDifferenteException();
     else
        return test(nombreSecret.getNombre());
   }

   /**
    * test : compare le NombreSecret avec une chaine de chiffre
    *
    * @param chaine à comparer
    * @return la chaine de caractères montrant les différences
    * @throws TailleDifferenteException si les deux nombres sont de taille différentes
    * @throws CaractereIncorrectException si un nombre ne comporte pas que des chiffres
    */
   public String test(String chaine) throws TailleDifferenteException, CaractereIncorrectException {
      StringBuilder sb = new StringBuilder();

      if(chaine.length()!=this.getTaille()) throw new TailleDifferenteException();

      for(int i=0; i<this.taille; i++){
         if (chaine.charAt(i) < '0') throw new CaractereIncorrectException();
         if (chaine.charAt(i) > '9') throw new CaractereIncorrectException();

         if(this.nombre.charAt(i)==chaine.charAt(i)) sb.append('=');
         if(this.nombre.charAt(i)<chaine.charAt(i))  sb.append('-');
         if(this.nombre.charAt(i)>chaine.charAt(i))  sb.append('+');
      }

      return log.traceExit(sb.toString());
   }

   @Override
   public void evaluateNext(String combinaison){
      StringBuilder sb = new StringBuilder();

      try {
         for (int i = 0; i < combinaison.length(); i++) {
            switch (combinaison.charAt(i)) {
               case '+':
                  // on défini une nouvelle borne inférieure
                  codeinf[i] = getNombre().charAt(i);
                  codeinf[i]++;
                  if (codeinf[i] > codesup[i]) {
                     codeinf[i] = '0';
                     codesup[i] = '9';
                     throw new BornageException();
                  }
                  // on cherche un chiffre entre les bornes min et max
                  sb.append( rand.nextInt(codesup[i] - codeinf[i] + 1) + codeinf[i] - '0');
                  break;
               case '-':
                  // on défini une nouvelle borne supérieur
                  codesup[i] = getNombre().charAt(i);
                  codesup[i]--;
                  if (codeinf[i] > codesup[i]) {
                     codeinf[i] = '0';
                     codesup[i] = '9';
                     throw new BornageException();
                  }
                  // on cherche un chiffre entre les bornes min et max
                  sb.append( rand.nextInt(codesup[i] - codeinf[i] + 1) + codeinf[i] - '0');
                  break;
               case '=':
                  // c'est le bon chiffre
                  sb.append(getNombre().charAt(i));
                  codeinf[i] = getNombre().charAt(i);
                  codesup[i] = getNombre().charAt(i);
                  break;
               default:
                  throw new CaractereIncorrectException();
            }
         }
      } catch (BornageException | CaractereIncorrectException e){
         log.error(e);
      }

      // on affecte la nouvelle valeur
      setNombre( sb.toString());
   }


}
