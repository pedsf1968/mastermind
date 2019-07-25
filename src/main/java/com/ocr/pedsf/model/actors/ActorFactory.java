package com.ocr.pedsf.model.actors;

import com.ocr.pedsf.model.MastermindProperties;

/**
 * ActorFactory : factory pour instancier les acteurs du jeu
 *
 * userActor : pour implémenter un utilisateur
 * robotActor : pour implémenter un robot
 */
public class ActorFactory {
   public static final int ACTOR_USER = 1;
   public static final int ACTOR_ROBOT = 2;

   private ActorFactory(){
      throw new IllegalStateException("Utility Class");
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
         case ACTOR_USER:
            return new User(name, mp);
         case ACTOR_ROBOT:
            return new Robot(name, mp);
         default:
            return new Robot(name, mp);
      }
   }

}
