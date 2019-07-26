package com.ocr.pedsf.model.codes;

import com.ocr.pedsf.exceptions.CaractereIncorrectException;
import com.ocr.pedsf.exceptions.TailleDifferenteException;

/**
 * Code : interface générale pour gérer les différents codages et règles
 *
 * @author pedsf
 * @version 1.0
 */

public interface Code {
   String getNombre();
   int getTaille();
   void setNombre(String nombre);
   void setTaille(int taille);

   /**
    * init : méthode d'initialisation avec un nombre aléatoire
    *
    * @param isSearching vaut true pour valider les bornes de recherche
    */
   void init(boolean isSearching);

   /**
    * test : compare le code avec un autre
    *
    * @param code à comparer
    * @return la chaine de caractères montrant les différences
    * @throws TailleDifferenteException si les deux nombres sont de taille différentes
    * @throws CaractereIncorrectException si un nombre ne comporte pas que des chiffres
    */
   String test(Code code) throws TailleDifferenteException, CaractereIncorrectException;

   /**
    * test : compare le NombreSecret avec une chaine de caractères
    *
    * @param chaine à comparer
    * @return la chaine de caractères montrant les différences
    * @throws TailleDifferenteException si les deux nombres sont de taille différentes
    * @throws CaractereIncorrectException si un nombre ne comporte pas que des chiffres
    */
   String test(String chaine) throws TailleDifferenteException, CaractereIncorrectException;

   /**
    * evaluateNext : méthode de recherche du nombre secret en fonction de la combinaison
    *                on doit faire un init(true) avant de l'utiliser pour initialiser les paramètres de recherche
    *
    * @param combinaison réponse de l'adversaire au code proposé
    */
   void evaluateNext(String combinaison);
}

