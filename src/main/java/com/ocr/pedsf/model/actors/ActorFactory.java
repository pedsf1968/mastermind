package com.ocr.pedsf.model.actors;


import com.ocr.pedsf.model.GameProperties;

/**
 * ActorFactory : factory pour instancier les acteurs du jeu
 *
 * userActor : pour implémenter un utilisateur
 * robotActor : pour implémenter un robot
 *
 * @author pedsf
 * @version 1.0
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
   public static Actor get(int actorType, String name, GameProperties mp){
      switch (actorType){
         case ACTOR_USER:
            return new UserActor(name, mp);
         case ACTOR_ROBOT:
            return new RobotActor(name, mp);
         default:
            return new RobotActor(name, mp);
      }
   }

}
