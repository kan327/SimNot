package app;

import java.io.*;
import java.nio.file.*;

public class Main {

    // ANSI color codes
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        if (args.length != 2 || !args[0].startsWith("make:")) {
            System.out.println(RED + "Usage: Frama make:<type> <name>" + RESET);
            return;
        }

        String rawType = args[0].split(":")[1];
        String baseType = normalizeType(rawType);
        String name = args[1];

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
        if (input == null || input.isEmpty()) return input;
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
