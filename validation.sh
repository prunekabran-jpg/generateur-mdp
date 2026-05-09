#!/bin/bash
# Script de validation d'un mot de passe avec CrackLib
# Reçoit le mot de passe en premier argument

PASSWORD=$1

# Vérification que le mot de passe est fourni
if [ -z "$PASSWORD" ]; then
    echo "Erreur : aucun mot de passe fourni"
    exit 1
fi

# Validation avec CrackLib
RESULT=$(echo "$PASSWORD" | cracklib-check 2>&1)

# Affichage du résultat
if echo "$RESULT" | grep -q "OK"; then
    echo "✅ Mot de passe validé par CrackLib"
else
    echo "⚠️  CrackLib : $RESULT"
fi