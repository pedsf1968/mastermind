package com.ocr.pedsf.model.actors;

import com.ocr.pedsf.model.MastermindProperties;

/**
 * ActorFactory : factory pour instancier les acteurs du jeu
 *
 * userActor : pour implémenter un utilisateur
 * robotActor : pour implémenter un robot
 */
public class ActorFactory {
   public final static int userActor = 1;
   public final static int robotActor = 2;

    public ActorFactory() {
   }

   /**
    * get : méthode pour obtenir une instanciation d'Actor
    *
    * @param actorType type reconnu per la class userActor ou robotActor
    * @param name nom de l'acteur
    * @param mp propriétés du jeu
    * @return une instance d'Actor
    */
   public static Actor get(int actorType, String name, MastermindProperties mp){
      switch (actorType){
         case userActor:
            return new User(name, mp);
         case robotActor:
            return new Robot(name, mp);
         default:
            return null;
      }
   }

}
