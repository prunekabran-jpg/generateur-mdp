# 🔐 generateur-mdp CLI (Java 21 + Docker)

## 📌 Contexte
Ce projet a été réalisé dans le cadre du cours **DevOps & Application Java**.  
Il s’agit d’un outil en ligne de commande (CLI) permettant de générer des mots de passe robustes et de valider leur solidité via un environnement Docker isolé.

---

## ⚙️ Fonctionnalités
- **Configuration personnalisée** : choix de la longueur et des types de caractères (majuscules, minuscules, chiffres, symboles).
- **Mode Rafale** : génération de plusieurs mots de passe en une seule exécution.
- **Indicateur de Force** : chaque mot de passe est évalué selon 5 niveaux :
  - Très faible
  - Faible
  - Moyen
  - Fort
  - Très fort
- **Interface CLI** : interaction uniquement via le terminal (arguments ou saisie utilisateur).
- **Interopérabilité Docker** : validation externe de la robustesse des mots de passe via un conteneur.

---

## 🛠️ Architecture Technique
- **Langage** : Java 21
- **Structure** :
  - `Main.java` → point d’entrée CLI
  - `generateurmdp.java` → logique de génération
  - `ValidateurDocker.java` → communication avec le conteneur Docker
- **Interopérabilité** :
  - Le programme Java envoie les mots de passe au conteneur.
  - Le conteneur utilise un outil de validation (ex. `cracklib`) pour calculer le score.
  - Le résultat est renvoyé au programme Java et affiché dans le terminal.

## 🚀 Installation & Exécution

 Cloner le projet
```bash
git clone https://github.com/prunekabran/generateur-mdp.git
cd generateur-mdp
