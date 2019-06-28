package com.ocr.pedsf.controler;

import com.ocr.pedsf.model.NombreSecret;

public interface Personnage {

   String getNom();
   NombreSecret getNs();
   NombreSecret getNsToSearch();

   /**
    * init : méthode pour initialiser le Personnage
    */
   void init();

   /**
    * attack : méthode du personnage qui attaque le personnage passé en paramètre
    *
    * @param personage attaqué
    *
    * @return true si c'est le bon code
    */
   boolean attack(Personnage personage);

   /**
    * reply : méthode de réponse du Personnage quand il reçoit une proposition
    *
    * @param nombreSecret proposé par l'adversaire
    * @return la réponse sous forme de +-=
    */
   public String reply(NombreSecret nombreSecret);

   /**
    * isEqual : méthode qui renvoie true si le NombreSecret passé en paramètre est le même que celui du Personnage
    *
    * @param nombreSecret à comparer avec celui du Personnage
    * @return true si les nombes sont identique false sinon
    */
   boolean isEqual(NombreSecret nombreSecret);

}
