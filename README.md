# Mastermind
Command line Mastermind game.

## Compilation
The lifecycle package create one two jar :
- mastermind.jar without dependencies.
- mastermind-jar-with-dependencies.jar that containts all neaded files.
 
``` java
mvn package
```
Use the command below to create javadoc :
```java
mvn javadoc:javadoc
```
## Exécution
Use the command below to start the game with java with unnecessary tags:
- -d : activate the debug mode.
- -u name : set the name for the user, default is "User".
- -m value : set the maximum length for the code, default is 10.
```
java -jar mastermind-jar-with-dependencies.jar [-d] [-u name] [-m value]
```
## Utilisation

In the starting menu the user can chose a new code length or start a new game mode.
The default lenght for the code is 4, define in the configuration file **mastermind.properties**.

### Mode challenger

The computer is the defender and the user is the challenger. The computer générate a code and the user send propositions to find the code.
On each caracter's code the computer respond with the sign :
- (-) if the caracter is lower.
- (+) if the caracter is bigger.
- (=) if the caracter is equal.
The number of trial 5 is specified in the  **mastermind.properties**. The user must find the code in the specified trials.

### Mode defender

The user is the defender and the computer is the challenger. 
The user set his code and the computer ask him propositions to find the code.
The user answer with a code string (-+=) for each caracter's code with this rule :
- (-) if the caracter is lower.
- (+) if the caracter is bigger.
- (=) if the caracter is equal.
The number of trial 5 is specified in the  **mastermind.properties**. The computer must find the code in the specified trials.


### Mode duel

The user and the computer are both challenger and defender. 
The user specify his code and the computer generate a random's one.
The user is the first challenger and the game stop when one code is found. 

### Mode autobaston

It's a duel between two computers.

## Architecture

It's a remaka of the **Mastermind** game in command line with the MVC pattern..

### Package **controler.modes**

Contain all controlers of MVC pattern.
- **Mode** global interface for all modes.

- **Jeu** the main controler for the starting menu to select : challenger, defender, duel and autobaston.
- **AutoBaston** computer versus computer mode implementation.
- **Challenger** user challenger mode implementation.
- **Defenseur** user defender mode implementation.
- **Duel** user versus computer mode implementation.
 
### Package **model**

Contains models of MVC pattern :
- **MastermindProperties** loader for the game properties.

### sous-pachage **model.codes**
- **Code** global interface for all secret code.
- **NombreSecret** simplefied mastermind code implémentation. 

### sous-pachage **model.actors**
- **Actor** global interface for game actors.
- **ActorFactory** factory that implement actors.
- **Robot** computer's actor implementation.
- **User** user's actor implementation.

### Package **vue**

Contain vues of MVC pattern.
- **Ask** global class for asking something to the user
- **AskCodeLength** ask to the user a new code length.
- **AskProposition** ask the user a code proposition.
- **AskAnswer** ask the answer to to a proposition.
- **Result** diplay the results.

#
### Package **exceptions**

Contain game exceptions.


### Package **resources**
Contain property files.
 - **mastermind.properties** for the game configuration.
 - **log4j2.xml** for logger configuration. 
 
 ### fichier mastermind.properties
 Contain games parameters and the tag definitions for the args in command line.
 
 #### global properties
 ``` java
 mastermind.combinaison.chiffres=4
 mastermind.combinaison.maxdigit=10
 mastermind.combinaison.essais=10
 ```
 
 ####Debug mode activation
 ``` java
 mastermind.mode.developpeur=false
 ```
 
 ####Actors names
 ``` java
 mastermind.nom.user=Utilisateur
 mastermind.nom.robot1=Chapi
 mastermind.nom.robot2=Chapo
 ```
 
 #####Tags for the command line
 ``` java
 mastermind.tag.debug=-d
 mastermind.tag.user=-u
 mastermind.tag.maxdigit=-m
```

