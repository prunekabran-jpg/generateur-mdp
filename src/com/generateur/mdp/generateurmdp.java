package com.generateur.mdp;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class generateurmdp {
    //  Amelioration de la generation avec verification de longueur minimale

    private static final String MAJUSCULES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULES = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHIFFRES = "0123456789";
    private static final String SYMBOLES = "!@#$%^&*()_+-=[]{}|;':\",./<>?";

    // Générateur aléatoire sécurisé
    private final SecureRandom random = new SecureRandom();

    /**
     * Génère un mot de passe selon les paramètres fournis.
     *
     * @param longueur    Longueur du mot de passe
     * @param majuscules  Inclure des majuscules
     * @param minuscules  Inclure des minuscules
     * @param chiffres    Inclure des chiffres
     * @param symboles    Inclure des symboles
     * @return Le mot de passe généré
     */
    public String generer(int longueur, boolean majuscules, boolean minuscules,
                          boolean chiffres, boolean symboles) {

        // Construction du pool de caractères disponibles
        StringBuilder pool = new StringBuilder();
        List<Character> garantis = new ArrayList<>();

        if (majuscules) {
            pool.append(MAJUSCULES);
            garantis.add(MAJUSCULES.charAt(random.nextInt(MAJUSCULES.length())));
        }
        if (minuscules) {
            pool.append(MINUSCULES);
            garantis.add(MINUSCULES.charAt(random.nextInt(MINUSCULES.length())));
        }
        if (chiffres) {
            pool.append(CHIFFRES);
            garantis.add(CHIFFRES.charAt(random.nextInt(CHIFFRES.length())));
        }
        if (symboles) {
            pool.append(SYMBOLES);
            garantis.add(SYMBOLES.charAt(random.nextInt(SYMBOLES.length())));
        }

        // Si aucun type n'est sélectionné, on utilise tout par défaut
        if (pool.isEmpty()) {
            pool.append(MAJUSCULES).append(MINUSCULES).append(CHIFFRES).append(SYMBOLES);
        }

        // Génération des caractères restants
        List<Character> caracteres = new ArrayList<>(garantis);
        for (int i = garantis.size(); i < longueur; i++) {
            caracteres.add(pool.charAt(random.nextInt(pool.length())));
        }

        // Mélange pour éviter que les caractères garantis soient toujours au début
        Collections.shuffle(caracteres, random);

        // Construction de la chaîne finale
        StringBuilder mdp = new StringBuilder();
        for (char c : caracteres) {
            mdp.append(c);
        }

        return mdp.toString();
    }

    /**
     * Génère plusieurs mots de passe en mode rafale.
     *
     * @param nombre     Nombre de mots de passe à générer
     * @param longueur   Longueur de chaque mot de passe
     * @param majuscules Inclure des majuscules
     * @param minuscules Inclure des minuscules
     * @param chiffres   Inclure des chiffres
     * @param symboles   Inclure des symboles
     * @return Liste des mots de passe générés
     */
    public List<String> genererRafale(int nombre, int longueur, boolean majuscules,
                                      boolean minuscules, boolean chiffres, boolean symboles) {
        List<String> mdps = new ArrayList<>();
        for (int i = 0; i < nombre; i++) {
            mdps.add(generer(longueur, majuscules, minuscules, chiffres, symboles));
        }
        return mdps;
    }
}