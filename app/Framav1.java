package app;

import java.io.*;
import java.nio.file.*;

public class Framav1 {

    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(RED + "Usage: Frama <command>" + RESET);
            return;
        }

        String command = args[0];

        if (command.equals("migrate")) {
            runMigration();
        } else if (args.length == 2 && command.startsWith("make:")) {
            handleMakeCommand(command, args[1]);
        } else {
            System.out.println(RED + "Unknown or invalid command." + RESET);
        }
    }

    private static void handleMakeCommand(String rawCommand, String name) {
        String rawType = rawCommand.split(":")[1];
        String baseType = normalizeType(rawType);

        try {
            String result = generateFile(baseType, name);
            String label = capitalize(baseType);
            String destPath = result.replace("\\", "/");

            if (result.equals("EXISTS")) {
                System.out.println();
                System.out.println(
                        CYAN + "INFO  " + RESET + label + " [" + destPath + "] "
                        + YELLOW + "already exists." + RESET
                );
                System.out.println();
            } else {
                System.out.println();
                System.out.println(
                        CYAN + "INFO  " + RESET + label + " [" + destPath + "] "
                        + GREEN + "created successfully." + RESET
                );
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println(RED + "FAILED: " + e.getMessage() + RESET);
        }
    }

    private static void runMigration() {
        System.out.print(YELLOW + "WARNING: This will delete all data if exists. Are you sure? (Y/n): " + RESET);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String input = reader.readLine();
            if (!input.equalsIgnoreCase("Y")) {
                System.out.println(RED + "Migration cancelled." + RESET);
                return;
            }

            System.out.println(CYAN + "\nCompiling migration files..." + RESET);

            Process compile = new ProcessBuilder(
                    "javac", "-d", "bin",
                    "src/config/DBConnection.java",
                    "src/config/Migrations.java"
            ).inheritIO().start();
            compile.waitFor();

            System.out.print(CYAN + "Running migrations" + RESET);

            // Animasi loading di thread terpisah
            Thread loading = new Thread(() -> {
                try {
                    for (int i = 0; i < 30; i++) {
                        Thread.sleep(150);
                        System.out.print(".");
                    }
                } catch (InterruptedException ignored) {
                }
            });

            loading.start();

            // Jalankan migrations
            Process run = new ProcessBuilder(
                    "java", "-cp", "bin;lib/mysql-connector-j-9.2.0.jar", "config.Migrations"
            ).start();

            BufferedReader outputReader = new BufferedReader(new InputStreamReader(run.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(run.getErrorStream()));

            String line;
            loading.join(); // tunggu loading selesai
            System.out.println(); // newline setelah animasi

            System.out.println(GREEN + "Migration Output:" + RESET);
            while ((line = outputReader.readLine()) != null) {
                System.out.println(GREEN + "✓ " + line + RESET);
            }

            while ((line = errorReader.readLine()) != null) {
                System.out.println(RED + "✗ " + line + RESET);
            }

            run.waitFor();

        } catch (Exception e) {
            System.out.println(RED + "Migrations failed: " + e.getMessage() + RESET);
        }
    }

    private static String generateFile(String baseType, String name) throws IOException {
        String className = capitalize(name);
        String suffix;

        switch (baseType) {
            case "controller":
                suffix = "Controller.java";
                break;
            case "view":
                suffix = "View.java";
                break;
            default:
                suffix = ".java";
        }

        String templatePath = "app/templates/" + baseType + ".template";
        String destDir = "src/" + baseType + "s";
        String destPath = destDir + "/" + className + suffix;

        Path dest = Paths.get(destPath);
        if (Files.exists(dest)) {
            return "EXISTS";
        }

        String template = new String(Files.readAllBytes(Paths.get(templatePath)));
        String result = template.replace("{{ClassName}}", className);

        Files.createDirectories(Paths.get(destDir));
        Files.write(dest, result.getBytes());

        return destPath;
    }

    private static String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    private static String normalizeType(String input) {
        input = input.toLowerCase();
        if (input.endsWith("s")) {
            input = input.substring(0, input.length() - 1);
        }

        switch (input) {
            case "model":
            case "controller":
            case "view":
                return input;
            default:
                System.out.println(RED + "Unsupported type: " + input + RESET);
                System.exit(1);
                return null;
        }
    }
}
