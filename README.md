# Projet DFV

Bienvenue sur le dépôt de **DFV**. Suivez ces instructions pour installer et exécuter le projet sur votre machine locale.

## Prérequis

Avant de commencer, assurez-vous d'avoir installé toutes les dépendances nécessaires :

- Git
- Node.js et npm

## Installation

### Clonage du projet Backend

1. Clonez le dépôt du projet backend à l'aide de Git :

```bash
git clone https://github.com/SebGaillard1/DVF_BACKEND.git
```

2. Ajoutez le fichier `full.csv` à la racine du projet backend cloné.

### Clonage et Configuration du projet Frontend

1. Clonez le dépôt du projet frontend à l'aide de Git :

    ```bash
    git clone https://github.com/Matteo-Nossro/DFV
    ```

2. Naviguez dans le dossier du projet frontend :

    ```bash
    cd DFV_front
    ```

3. Installez les dépendances nécessaires et démarrez le serveur de développement :

    ```bash
    npm install
    npm run dev
    ```

## Configuration pour des Transactions Immédiates

Afin d'obtenir des transactions dès le lancement de l'application, configurez les paramètres suivants :

- **Longitude** : `6.019949`
- **Latitude** : `46.24745`
- **Rayon** : `1000`

Incorporez ces paramètres dans les configurations de l'application ou utilisez-les dans les requêtes API.

## Accès à Swagger

Pour visualiser et interagir avec l'API du projet, accédez à l'interface Swagger à l'URL suivante :

- http://localhost:8080/swagger-ui/index.html
