package com.ocr.pedsf.model.actors;

import com.ocr.pedsf.model.codes.NombreSecret;

/**
 * Actor : interface générale pour les acteurs du jeu User ou Robot
 *
 * @author PEDSF
 * @version 1.0
 */
public interface Actor {

   String getNom();
   NombreSecret getNs();
   void setNs();
   NombreSecret getNsToSearch();

   /**
    * attack : méthode du actors qui attaque le actors passé en paramètre
    *
    * @param adversaire attaqué
    *
    * @return true si c'est le bon code
    */
   boolean attack(Actor adversaire);

   /**
    * reply : méthode de réponse du Actor quand il reçoit une proposition
    *
    * @param nombreSecret proposé par l'adversaire
    * @return la réponse sous forme de +-=
    */
   String reply(NombreSecret nombreSecret);

   /**
    * isEqual : méthode qui renvoie true si le NombreSecret passé en paramètre est le même que celui du Actor
    *
    * @param nombreSecret à comparer avec celui du Actor
    * @return true si les nombes sont identique false sinon
    */
   boolean isEqual(NombreSecret nombreSecret);
}
