package com.generateur.mdp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Classe responsable de la validation des mots de passe via un conteneur Docker.
 * Utilise CrackLib dans un conteneur Docker pour vérifier la robustesse réelle.
 */
public class ValidateurDocker {

    // Nom de l'image Docker utilisée pour la validation
    private static final String IMAGE_DOCKER = "cracklib-validator";

    /**
     * Valide un mot de passe en utilisant CrackLib dans un conteneur Docker.
     * Lance un conteneur temporaire qui s'auto-détruit après utilisation.
     *
     * @param mdp Le mot de passe à valider
     * @return Le résultat de la validation (OK ou raison du rejet)
     */
    public String valider(String mdp) {
        try {
            // Construction de la commande Docker
            // --rm : supprime le conteneur après exécution
            ProcessBuilder pb = new ProcessBuilder(
                    "docker", "run", "--rm",
                    IMAGE_DOCKER,
                    mdp
            );

            // Fusion des flux d'erreur et de sortie standard
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Lecture du résultat
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            StringBuilder resultat = new StringBuilder();
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                resultat.append(ligne);
            }

            process.waitFor();
            return resultat.toString().trim();

        } catch (Exception e) {
            // Si Docker n'est pas disponible, on retourne une erreur claire
            return "Erreur Docker : " + e.getMessage();
        }
    }

    public boolean dockerDisponible() {
        try {
            ProcessBuilder pb = new ProcessBuilder("docker", "info");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }
}