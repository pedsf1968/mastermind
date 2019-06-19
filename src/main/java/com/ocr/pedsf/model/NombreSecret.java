package com.ocr.pedsf.model;

import com.ocr.pedsf.exceptions.TailleDifferenteException;

import java.util.Objects;

/**
 * NombreSecret : class pour gérer les chaines de nombres
 */
public class NombreSecret {
   private String nombre;
   private int taille;

   public NombreSecret(String nombre) {
      this.nombre = nombre;
      this.taille = nombre.length();
   }

   public NombreSecret(int taille) {
      this.taille = taille;
      this.nombre = initNombreTailleN(taille);
   }

   public String getNombre() {
      return nombre;
   }
   public int getTaille() {
      return taille;
   }

   public String initNombreTailleN(int n){
      StringBuilder sb = new StringBuilder();

      for(int i=0; i<n; i++) {
         sb.append(Math.round(Math.floor( (Math.random() * 10.0))));
      }

      return sb.toString();
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
    * @throws TailleDifferenteException
    */
   public String test(NombreSecret nombreSecret) throws TailleDifferenteException {
      StringBuilder sb = new StringBuilder();

     if(nombreSecret.getTaille()!=this.taille) throw new TailleDifferenteException();

      for(int i=0; i<this.taille; i++){
         if(this.nombre.charAt(i)==nombreSecret.nombre.charAt(i))
            sb.append('=');
         if(this.nombre.charAt(i)<nombreSecret.nombre.charAt(i))
            sb.append('-');
         if(this.nombre.charAt(i)>nombreSecret.nombre.charAt(i))
            sb.append('+');
      }
      return sb.toString();
   }

   /**
    * test : compare le NombreSecret avec une chaine
    *
    * @param chaine à comparer
    * @return la chaine de caractères montrant les différences
    * @throws TailleDifferenteException
    */
   public String test(String chaine) throws TailleDifferenteException {
      StringBuilder sb = new StringBuilder();

      if(chaine.length()!=this.taille) throw new TailleDifferenteException();

      for(int i=0; i<this.taille; i++){
         if(this.nombre.charAt(i)==chaine.charAt(i))
            sb.append('=');
         if(this.nombre.charAt(i)<chaine.charAt(i))
            sb.append('-');
         if(this.nombre.charAt(i)>chaine.charAt(i))
            sb.append('+');
      }
      return sb.toString();
   }

}
