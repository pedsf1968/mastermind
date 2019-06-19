# Mastermind
Jeu mastermind en ligne de commande


## Utilisation

Dans le menu d'accueil l'utilisateur peut choisir le mode de jeu et définir une nouvelle longueur pour les codes.

La taille par défaut est définie dans le fichier de configuration *mastermind.properties*

### Mode challenger

L'intelligence artificielle de l’ordinateur joue le rôle de défenseur. Elle définit une combinaison de X chiffres aléatoirement.
Le joueur a le rôle d’attaquant et doit faire une proposition d’une combinaison de X chiffres.
L'intelligence artificielle de l’ordinateur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=).
Il y a un nombre limité d’essais spécifié dans le fichier de configuration.

### Mode defenseur

Le joueur (cette fois dans le rôle de défenseur) définit une combinaison de X chiffres aléatoirement.
L'intelligence artificielle de l’ordinateur doit faire une proposition d’une combinaison de X chiffres (c’est le rôle attaquant).
Le joueur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=).
L’intelligence artificielle fait une autre proposition en se basant sur la réponse fournit par le joueur.
Il y a un nombre limité d’essais.

## Mode duel

Le joueur et l’intelligence artificielle de l’ordinateur jouent tour à tour. Le premier à trouver la combinaison secrète de l'autre a gagné ! 


## Architecture

Le programme *Mastermind* est une version du jeu éponyme en ligne de commande avec le pattern MVC.

### Package *controler*

Il contient les class Controler du mode MVC :
- *IA* pour gérer la pseudo intelligence de l'ordinateur.
- *Jeu* pour le déroulement des différents modes du jeu : challenger, defenseur et duel.

### Package *model*

Il renferme les class des modèles du MVC :
- *MastermindProperties* qui se charge des propriétés de l'application et le chargement de la configuration.
- *NombreSecret* qui gère les codes

### Package *vue*

Il regroupe les différents écrans du jeu dans la class *Affichage*.

### Package *utils*

Contient la class *GestionSaisie* de vérification des saisies de l'utilisateur

### Package *exceptions*

pour la gestion des exceptions.

### Package resources
comporte le fichier de configuration *mastermind.properties* 



