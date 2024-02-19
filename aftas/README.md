# Contexte du Projet
## Architecture Générale :
1. **Frontend (Interface Utilisateur) :**
   - **Technologie :** Angular
   - **Fonctionnalités :**
     - Inscription des adhérents aux compétitions.
     - Affichage des compétitions disponibles.
     - Enregistrement des résultats des compétitions.
     - Affichage du podium.
     - Recherche d'adhérents.

2. **Backend (Logique Métier et Traitement des Données) :**
   - **Technologie :** Java Spring Boot
   - **Fonctionnalités :**
     - Gestion des adhérents (CRUD - Création, Lecture, Mise à jour, Suppression).
     - Gestion des compétitions (CRUD).
     - Vérification de la disponibilité d'une compétition.
     - Enregistrement des résultats de la chasse.
     - Calcul du podium.

3. **Base de Données :**
   - **Technologie :** PostgreSQL
   - **Schéma :**
     - Adhérents :
       - Numéro d'adhésion
       - Nom, prénom
       - Pièce d'identification, nationalité
       - Date d'adhésion
     - Compétitions :
       - Code (chaîne de caractères)
       - Date, heure de début et de fin
       - Nombre de participants
       - Lieu
       - Liste des participants (référence aux adhérents)
       - Résultats (nombre de poissons chassés pour chaque participant)

4. **Serveur API (Interface entre Frontend et Backend) :**
   - **Technologie :** Java Spring Boot avec annotations
   - **Endpoints :**
     - Adhérents (CRUD)
     - Compétitions (CRUD)
     - Inscription des adhérents aux compétitions
     - Enregistrement des résultats
