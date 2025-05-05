package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class BaseTest {
    abstract protected void inicializalas();
    abstract protected void parancsFeldolgozas(String parancs);
    abstract public void runTest();
    abstract String getResult();

    protected void saveTestResult(StringBuilder log, String tesztSzam) {
        String directoryPath = "tests" + File.separator + "test_" + tesztSzam;
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                System.out.println("Nem sikerült létrehozni a mappát: " + directoryPath);
                return;
            }
        }

        File outputFile = new File(directory, "teszt_"+ tesztSzam + ".txt");

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(log.toString());
            writer.flush();
            System.out.println("Teszt eredmény sikeresen mentve: " + outputFile.getPath());
        } catch (IOException e) {
            System.out.println("Hiba a tesztfájl írásakor: " + e.getMessage());
        }
    }
}
