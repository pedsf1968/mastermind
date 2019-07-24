package com.ocr.pedsf.model;

import com.ocr.pedsf.exceptions.CaractereIncorrectException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * NombreSecret : class pour gérer les chaines de nombres
 *
 * @author pedsf
 * @version 1.0
 */
public class NombreSecret {
   private static final Logger log = LogManager.getLogger(NombreSecret.class);
   private String nombre = "";
   private int taille;

   public NombreSecret(String nombre) {
      this.nombre = nombre;
      this.taille = nombre.length();
   }

   public NombreSecret(int taille) {
      this.taille = taille;
      init();
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
      init();
   }

   /**
    * init : méthode d'initialisation avec un nombre aléatoire
    */
   public void init(){
      StringBuilder sb = new StringBuilder();

      for(int i=0; i<this.taille; i++) {
         sb.append(Math.round(Math.floor( (Math.random() * 10.0))));
      }

      this.nombre = sb.toString();
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
    * @param nombreSecret à comparer
    * @return la chaine de caractères montrant les différences
    * @throws TailleDifferenteException si les deux nombres sont de taille différentes
    * @throws CaractereIncorrectException si un nombre ne comporte pas que des chiffres
    */
   public String test(NombreSecret nombreSecret) throws TailleDifferenteException, CaractereIncorrectException {

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


}
