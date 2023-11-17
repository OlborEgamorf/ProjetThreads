# Projet Compétences Informatique, Spécialisation Projet
## Licence MIASHS Semestre 3, 2022-2023
Projet réalisé par Adrian Guilhem, Elisa Cezard, Khalil Makhloufi et Lucas TRIOZON

Lien vers le rapport : [Rapport Projet.pdf](https://github.com/OlborEgamorf/ProjetInfoS3/blob/main/Rapport%20Projet.pdf)

## Introduction (extrait du rapport)
Le projet à rendre en ce semestre 3 pour l’UE TE38XI est une machine SMA, simulation multi-agent. Le sujet est libre du moment où sont réunies deux conditions : faire une simulation avec une interface graphique et que nos agents soient des Threads indépendants.

C’est un travail de groupe où chacun a réalisé des tâches parmi une liste établie et mise à jour en fonction de notre avancée.

Après concertation, nous nous sommes orientés vers la simulation d’une plage, et plus précisément des actions et événements entre les individus qui se déroulent sur une plage.

Le projet fonctionnera donc avec deux composantes essentielles : une plage et des personnes. Chaque individu a comme caractéristiques son âge, sa vitesse, sa capacité à sauver des gens, sa probabilité à se noyer. La plage a comme caractéristiques sa longueur, sa largeur, la taille de la mer, la température et la vitesse du vent.

En plus des caractéristiques, chaque classe possède des attributs plus particuliers pour faire fonctionner la simulation, pour lesquels on reviendra dessus dans l’approche.

Les personnes vont arriver progressivement sur la plage, placer leurs affaires où elles le souhaitent, puis faire des aller et retours dans la mer pour se baigner, puis ultimement partir. Elles pourront se noyer, être sauvées et acheter des choses auprès d’un vendeur itinérant. 

Avant la simulation, l’utilisateur aura la possibilité de choisir ses paramètres, pour avoir une simulation sur mesure. 

Tout sera affiché dans une interface graphique faite par nos soins, avec des points qui se déplacent, représentant les individus sur la plage.
