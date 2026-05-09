# Image de base Linux légère
FROM debian:bullseye-slim

# Installation de CrackLib pour la validation des mots de passe
RUN apt-get update && \
    apt-get install -y libcrack2 cracklib-runtime && \
    apt-get clean

# Script de validation
COPY validation.sh /validation.sh
RUN chmod +x /validation.sh

# Point d'entrée : reçoit le mot de passe en argument
ENTRYPOINT ["/validation.sh"]