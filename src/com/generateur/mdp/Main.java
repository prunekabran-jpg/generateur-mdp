package com.generateur.mdp;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        generateurmdp generateur = new generateurmdp();
        Forcemdp forcemdp = new Forcemdp();
        ValidateurDocker validateur = new ValidateurDocker();

        System.out.println(" 🔐 Générateur de Mots de Passe 🔐  ║");


        // Vérification de Docker
        if (validateur.dockerDisponible()) {
            System.out.println("✅ Docker est disponible - Validation avancée activée");
        } else {
            System.out.println("⚠️  Docker non disponible - Validation basique uniquement");
        }
        System.out.println();

        // Saisie de la longueur
        System.out.print("📏 Longueur du mot de passe (min 8) : ");
        int longueur = scanner.nextInt();
        if (longueur < 8) {
            System.out.println("⚠️  Longueur minimale : 8. Valeur ajustée à 8.");
            longueur = 8;
        }

        // Saisie des types de caractères
        System.out.println("\n🔠 Types de caractères à inclure :");
        System.out.print("   Majuscules (A-Z) ? (o/n) : ");
        boolean majuscules = scanner.next().equalsIgnoreCase("o");

        System.out.print("   Minuscules (a-z) ? (o/n) : ");
        boolean minuscules = scanner.next().equalsIgnoreCase("o");

        System.out.print("   Chiffres (0-9) ? (o/n) : ");
        boolean chiffres = scanner.next().equalsIgnoreCase("o");

        System.out.print("   Symboles (!@#...) ? (o/n) : ");
        boolean symboles = scanner.next().equalsIgnoreCase("o");

        // Mode rafale
        System.out.print("\n🔁 Combien de mots de passe voulez-vous générer ? : ");
        int nombre = scanner.nextInt();
        if (nombre < 1) nombre = 1;


        System.out.println("🔑 Mots de passe générés");



        List<String> mdps = generateur.genererRafale(nombre, longueur,
                majuscules, minuscules, chiffres, symboles);

        for (int i = 0; i < mdps.size(); i++) {
            String mdp = mdps.get(i);
            Forcemdp.Niveau niveau = forcemdp.calculerForce(mdp);

            System.out.println("\n📌 Mot de passe " + (i + 1) + " :");
            System.out.println("   🔑 " + mdp);
            System.out.println("   💪 Force : " + niveau.getLibelle());

            // Validation Docker si disponible
            if (validateur.dockerDisponible()) {
                String resultatDocker = validateur.valider(mdp);
                System.out.println("   🐳 Validation Docker : " + resultatDocker);
            }
        }
        System.out.println("✅ Génération terminée !");
        scanner.close();
    }
}
