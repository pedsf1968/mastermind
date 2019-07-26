package com.ocr.pedsf.model.actors;

import com.ocr.pedsf.model.codes.Code;

/**
 * Actor : interface générale pour les acteurs du jeu User ou Robot
 *
 * @author PEDSF
 * @version 1.0
 */
public interface Actor {

   String getNom();
   Code getNs();
   void setNs();
   Code getNsToSearch();

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
    * @param code proposé par l'adversaire
    * @return la réponse sous forme de +-=
    */
   String reply(Code code);

   /**
    * isEqual : méthode qui renvoie true si le Code passé en paramètre est le même que celui du Actor
    *
    * @param code à comparer avec celui du Actor
    * @return true si les nombes sont identique false sinon
    */
   boolean isEqual(Code code);
}
