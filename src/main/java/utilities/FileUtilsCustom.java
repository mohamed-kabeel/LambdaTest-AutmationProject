package utilities;

import java.io.*;
import java.nio.file.*;
import java.util.Comparator;
import java.util.Properties;

public class FileUtilsCustom {

    public static String getDataFromProperties(String path, String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(path));
        return properties.getProperty(key);
    }

    public static void cleanAllureResults() {
        Path folder = Paths.get("allure-results");
        try {
            if (Files.exists(folder)) {
                Files.walk(folder)
                        .sorted(Comparator.reverseOrder())
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                            } catch (IOException ignored) {}
                        });
            }
        } catch (IOException ignored) {}
    }

    public static void deleteFolderCompletely(String folderPath) {
        Path folder = Paths.get(folderPath);
        try {
            if (Files.exists(folder)) {
                Files.walk(folder)
                        .sorted(Comparator.reverseOrder())
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                System.err.println("Failed to delete: " + path + " - " + e.getMessage());
                            }
                        });
                System.out.println("Folder deleted: " + folderPath);
            } else {
                System.out.println("Folder does not exist: " + folderPath);
            }
        } catch (IOException e) {
            System.err.println("Error while deleting folder: " + e.getMessage());
        }
    }

    public static void cleanFolderContents(String folderPath) {
        Path folder = Paths.get(folderPath);
        if (!Files.exists(folder)) {
            System.out.println("Folder does not exist: " + folderPath);
            return;
        }

        try {
            Files.walk(folder, 1)
                    .filter(path -> !path.equals(folder))
                    .forEach(path -> {
                        try {
                            if (Files.isDirectory(path)) {
                                Files.walk(path)
                                        .sorted(Comparator.reverseOrder())
                                        .forEach(p -> {
                                            try {
                                                Files.delete(p);
                                            } catch (IOException e) {
                                                System.err.println("Failed to delete: " + p + " -> " + e.getMessage());
                                            }
                                        });
                            } else {
                                Files.delete(path);
                            }
                        } catch (IOException e) {
                            System.err.println("Failed to process: " + path + " -> " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.err.println("Error cleaning folder: " + folderPath + " -> " + e.getMessage());
        }
    }
}
