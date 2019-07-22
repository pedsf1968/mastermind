# Mastermind
Jeu mastermind en ligne de commande

## Compilation
Lancez le lifecycle package pour créer les archives l'une avec et l'autre sans dépendance.
 
``` java
mvn package
```
Pour obtenir la Javadoc lancez la commande Maven suivante:
```java
mvn javadoc:javadoc
```
## Exécution
```
java -jar mastermind-jar-with-dependencies.jar [-d] [-u nom_utilisateur] [-m valeur]
```
## Utilisation

Dans le menu d'accueil l'utilisateur peut choisir le mode de jeu et définir une nouvelle longueur pour les codes.

La taille par défaut est définie dans le fichier de configuration **mastermind.properties**

### Mode challenger

L'ordinateur joue le rôle de défenseur. Elle définit une combinaison de X chiffres aléatoirement.
Le joueur a le rôle d’attaquant et doit faire une proposition d’une combinaison de X chiffres.
L'ordinateur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=).
Il y a un nombre limité d’essais spécifié dans le fichier de configuration.

### Mode defenseur

Le joueur (cette fois dans le rôle de défenseur) définit une combinaison de X chiffres aléatoirement.
L’ordinateur doit faire une proposition d’une combinaison de X chiffres (c’est le rôle attaquant).
Le joueur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=).
L’intelligence artificielle fait une autre proposition en se basant sur la réponse fournit par le joueur.
Il y a un nombre limité d’essais.

### Mode duel

Le joueur et l’intelligence artificielle de l’ordinateur jouent tour à tour. Le premier à trouver la combinaison secrète de l'autre a gagné ! 



## Architecture

Le programme **Mastermind** est une version du jeu éponyme en ligne de commande avec le pattern MVC.

### Package **controler**

Il contient les class Controler du mode MVC :
- **Jeu** est une classe qui gère les choix des modes de jeu : challenger, defenseur, duel et autobaston.

- **Mode** est une interface commune aux différents modes de jeu.

#### sous-package **controler.modes**
- **AutoBaston** implémentation du mode de jeu ordinateur contre ordinateur.
- **Challenger** implémentation du mode de jeu où l'utilisateur attaque l'ordinateur.
- **Defenseur** implémentation du mode de jeu où l'ordinateur attaque l'utilisateur.
- **Duel** implémentation du mode de jeu où l'utilisateur et l'ordinateur s'attaquent à tour de rôle.
 
### Package **model**

Il renferme les class des Modèles du MVC :
- **MastermindProperties** qui se charge des propriétés de l'application et le chargement de la configuration.
- **NombreSecret** qui gère les codes.
- **Personnage** interface pour gérer les protagonistes.

### sous-pachage **model.personnages**
- **Robot** implémentation de Personnage pour le protagoniste ordinateur.
- **User** implémentation de Personnage pour le protagoniste utilisateur.

### Package **vue**

Il regroupe les différents écrans du jeu.
- **ChoixDuMode** demande du mode de jeu.
- **ChoixNombreDigit** demande la taille du code en nombre de digit.
- **DemandeProposition** demande à l'utilisateur de faire une proposition de code.
- **DemandeReponse** demande à l'utilisateur de faire une réponse à une proposition.
- **Resultat** affiche le résultat d'uin jeu.

#
### Package **exceptions**

pour la gestion des exceptions.

### Package **resources**
comporte le fichier de configuration :
 - **mastermind.properties** pour la configuration du jeu.
 - **log4j2.xml** pour la configuration de log4j2. 
 
 ### fichier mastermind.properties
 Il contient les paramètres de l'application ainsi 
 que les tags utilisés pour redéfinir certains paramètres en ligne de commande lors du lancement.
 
 Contenu originel du fichier :
 #### propriétés générales de mastermind
 ``` java
 mastermind.combinaison.chiffres=4
 mastermind.combinaison.maxdigit=10
 mastermind.combinaison.essais=10
 ```
 
 ####Activation du mode développeur
 ``` java
 mastermind.mode.developpeur=false
 ```
 
 ####Nom des personnages
 ``` java
 mastermind.nom.user=Utilisateur
 mastermind.nom.robot1=Chapi
 mastermind.nom.robot2=Chapo
 ```
 
 #####Définition des tags pour définir les paramètres en ligne de commande
 ``` java
 mastermind.tag.debug=-d
 mastermind.tag.user=-u
 mastermind.tag.maxdigit=-m
```

