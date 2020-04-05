# battle-api-welloo

## But du projet
API permettant de représenter un combat entre deux dresseurs de pokémon sous format json

## Lancer l'application
Pour lancer l'application, il suffit d'exécuter la commande suivante:
```
mvn clean install && mvn spring-boot:run
```
L'application sera accessible via l'url http://localhost:9001/  
Cette API utilise les API pokemon-type et trainer et nécessite donc que celles-ci soient en fonctionnement, il est possible de modifier le chemin d'accès à ces API directement dans le fichier application.properties.

Routes disponibles:
- POST /battles : Prend 2 paramètres (noms des 2 dresseurs en paramètres). Crée une instance de combat, et retourne un UUID permettant de l’identifier.
- GET /battles : liste les combats en cours
- GET /battles/{uuid} : Récupère l’état d’un combat en cours
- POST /battles/{uuid}/{trainerName}/attack : Permet à un dresseur de donner un ordre d’attaque pendant le combat. Retourne l’état du combat.

## Exécuter les tests
Pour exécuter les tests, utiliser la commande suivante:
```
mvn test
```

## Déploiement
L'application n'a pas été déployé

## Annexes
Un swagger a été mis en place et est accessible via l'url http://localhost:9001/swagger-ui.html#/  
Des tests postman sont disponibles dans le fichier `src/test/resources/battle-api.postman_collection.json`

## Auteur

Christopher DUCROCQ [@well-oo](https://github.com/well-oo)  
Etudiant en Master 2 MIAGE FA