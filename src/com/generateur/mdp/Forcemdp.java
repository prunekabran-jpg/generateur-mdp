package com.generateur.mdp;

/**
 * Classe responsable du calcul du score de robustesse d'un mot de passe.
 * Analyse plusieurs critères pour déterminer la force du mot de passe.
 */
public class Forcemdp {

    /**
     * Niveaux de force d'un mot de passe
     */
    public enum Niveau {
        TRES_FAIBLE("Très faible ❌"),
        FAIBLE("Faible ⚠️"),
        MOYEN("Moyen 🔶"),
        FORT("Fort ✅"),
        TRES_FORT("Très fort 💪");

        private final String libelle;

        Niveau(String libelle) {
            this.libelle = libelle;
        }

        public String getLibelle() {
            return libelle;
        }
    }

    /**
     * Calcule le score de robustesse d'un mot de passe.
     *
     * @param mdp Le mot de passe à analyser
     * @return Le niveau de force du mot de passe
     */
    public Niveau calculerForce(String mdp) {
        int score = 0;

        // Critère 1 : longueur du mot de passe
        if (mdp.length() >= 8) score++;
        if (mdp.length() >= 12) score++;
        if (mdp.length() >= 16) score++;

        // Critère 2 : présence de majuscules
        if (mdp.matches(".*[A-Z].*")) score++;

        // Critère 3 : présence de minuscules
        if (mdp.matches(".*[a-z].*")) score++;

        // Critère 4 : présence de chiffres
        if (mdp.matches(".*[0-9].*")) score++;

        // Critère 5 : présence de symboles
        if (mdp.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}|;':\",./<>?].*")) score++;

        // Conversion du score en niveau
        if (score <= 1) return Niveau.TRES_FAIBLE;
        if (score <= 2) return Niveau.FAIBLE;
        if (score <= 4) return Niveau.MOYEN;
        if (score <= 6) return Niveau.FORT;
        return Niveau.TRES_FORT;
    }
}