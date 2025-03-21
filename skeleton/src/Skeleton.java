import TestCases.ITestCase;

import java.util.*;
import TestCases.*;

public class Skeleton {
    private static final String WELCOME_MESSAGE = """
            Fungorium Skeleton TestProgram
            For help type help and press enter after the > mark.
            For exit type exit after the > mark.
            """;

    private static final String HELP_MESSAGE = """
            - Write prompts after the > mark.
            - ls prompt: The ls prompt lists all of the test cases with numbers. In order to run a specific test, use these numbers as arguments in the run test <test_number> prompt.
            - run test <test_number>: The run test prompt runs the test, marked with the <test_number> sequence.
            - exit: The exit prompt terminates the Fungorium Skeleton TestProgram.
            """;

    private static final Map<Integer, String> TEST_CASE_NAMES = new LinkedHashMap<>();
    private static final Map<Integer, ITestCase> TEST_CASE_CLASSES = new LinkedHashMap<>();
    static {
        TEST_CASE_NAMES.put(1, "Játék Indítás Teszt - Tekton inicializálás");
        TEST_CASE_NAMES.put(2, "Játék Indítás Teszt - Rovarász inicializálás");
        TEST_CASE_NAMES.put(3, "Játék Indítás Teszt - Gombász inicializálás");
        TEST_CASE_NAMES.put(6, "Gombatest Szintlépés Teszt");
        TEST_CASE_NAMES.put(9, "Rovar Képességek alaphelyzetbe állítása teszt");
        TEST_CASE_NAMES.put(10, "Tekton Hatás Kifejtés: TektonHatas teszt");
        TEST_CASE_NAMES.put(11, "Tekton Hatás Kifejtés: FonalFelszivodoHatas teszt");
        TEST_CASE_NAMES.put(13, "Tekton Hatás Kifejtés: FonalGatloHatas teszt");
        TEST_CASE_NAMES.put(14, "Tekton Hatás Kifejtés: GombatestGatloHatas teszt");
        TEST_CASE_NAMES.put(15, "Tekton Kettétörés Teszt - van Rovar a tektonon");
        TEST_CASE_NAMES.put(16, "Tekton Kettétörés Teszt - nincs Rovar a tektonon");
        TEST_CASE_NAMES.put(17, "Gomba Spóra Termelés Teszt");
        TEST_CASE_NAMES.put(18, "Spóra Elfogyasztás - GyorsitoSpora teszt");
        TEST_CASE_NAMES.put(19, "Spóra Elfogyasztás - LassitoSpora teszt");
        TEST_CASE_NAMES.put(20, "Spóra Elfogyasztás - BenitoSpora teszt");
        TEST_CASE_NAMES.put(21, "Spóra Elfogyasztás - VagastGatloSpora teszt");
        TEST_CASE_NAMES.put(22, "Spóra Elfogyasztás - Spora teszt");
        TEST_CASE_NAMES.put(23, "Játék Kiértékelés Teszt");
        TEST_CASE_NAMES.put(24, "Gombafonal Elvágás Teszt");
        TEST_CASE_NAMES.put(25, "Gombafonal Folytonosság Megszakadása Teszt");
        TEST_CASE_NAMES.put(26, "Rovar Irány Megadás Teszt");
        TEST_CASE_NAMES.put(27, "Rovar Irány Megadás Teszt - Fonallal nem összekötött tektonok");
        TEST_CASE_NAMES.put(28, "Rovar Irány Megadás Teszt - Cél tektonon van Rovar");
        TEST_CASE_NAMES.put(29, "Spóra Szórás Teszt");
        TEST_CASE_NAMES.put(30, "Spóra Szórás Teszt - Nem szomszédos Tektonok");
        TEST_CASE_NAMES.put(31, "Spóra Szórás Teszt - Nincs elég spórakészlet");
        TEST_CASE_NAMES.put(32, "Spóra Szórás Teszt - Cél Tektonon már van GombaTest");
        TEST_CASE_NAMES.put(33, "Gombafonal Irányítás Teszt - Cél Tektonon van Spóra");
        TEST_CASE_NAMES.put(34, "Gombafonal Irányítás Teszt - Fonal nem lerakható");
        TEST_CASE_NAMES.put(36, "Gombatest Növesztés");
        TEST_CASE_NAMES.put(37, "Fonal Vásárlás Teszt");
        TEST_CASE_NAMES.put(38, "Gombafonal Folytonosság Megszakadása Teszt - Nincs megszakadás");
    }

    static {
        TEST_CASE_CLASSES.put(1, new Test1());
        TEST_CASE_CLASSES.put(2, new Test2());
        TEST_CASE_CLASSES.put(3, new Test3());
        TEST_CASE_CLASSES.put(6, new Test6());
        TEST_CASE_CLASSES.put(9, new Test9());
        TEST_CASE_CLASSES.put(10, new Test10());
        TEST_CASE_CLASSES.put(11, new Test11());
        TEST_CASE_CLASSES.put(13, new Test13());
        TEST_CASE_CLASSES.put(14, new Test14());
        TEST_CASE_CLASSES.put(15, new Test15());
        TEST_CASE_CLASSES.put(16, new Test16());
        TEST_CASE_CLASSES.put(17, new Test17());
        TEST_CASE_CLASSES.put(18, new Test18());
        TEST_CASE_CLASSES.put(19, new Test19());
        TEST_CASE_CLASSES.put(20, new Test20());
        TEST_CASE_CLASSES.put(21, new Test21());
        TEST_CASE_CLASSES.put(22, new Test22());
        TEST_CASE_CLASSES.put(23, new Test23());
        TEST_CASE_CLASSES.put(24, new Test24());
        TEST_CASE_CLASSES.put(25, new Test25());
        TEST_CASE_CLASSES.put(26, new Test26());
        TEST_CASE_CLASSES.put(27, new Test27());
        TEST_CASE_CLASSES.put(28, new Test28());
        TEST_CASE_CLASSES.put(29, new Test29());
        TEST_CASE_CLASSES.put(30, new Test30());
        TEST_CASE_CLASSES.put(31, new Test31());
        TEST_CASE_CLASSES.put(32, new Test32());
        TEST_CASE_CLASSES.put(33, new Test33());
        TEST_CASE_CLASSES.put(34, new Test34());
        TEST_CASE_CLASSES.put(36, new Test36());
        TEST_CASE_CLASSES.put(37, new Test37());
        TEST_CASE_CLASSES.put(38, new Test38());
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(WELCOME_MESSAGE);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("help")) {
                System.out.println(HELP_MESSAGE);
            } else if (input.equalsIgnoreCase("ls")) {
                listTests();
            } else if (input.startsWith("run test")) {
                runTest(input);
            } else if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting Fungorium Skeleton TestProgram...");
                break;
            } else {
                System.out.println("Invalid input, for help run the > help command");
            }
        }

        scanner.close();
    }

    private static void listTests() {
        System.out.println("Available tests:");
        for (Map.Entry<Integer, String> entry : TEST_CASE_NAMES.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void runTest(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            System.out.println("Invalid command format. Usage: run test <test_number>");
            return;
        }

        try {
            int testNumber = Integer.parseInt(parts[2]);
            ITestCase testCase = TEST_CASE_CLASSES.get(testNumber);
            if (TEST_CASE_NAMES.containsKey(testNumber)) {
                System.out.println("Running test: " + TEST_CASE_NAMES.get(testNumber));
                executeTest(testNumber, testCase);
            } else {
                System.out.println("Test not found. Use 'ls' to list available tests.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid test number format. Use an integer value.");
        }
    }

    private static void executeTest(int testNumber, ITestCase testCase) {
        System.out.println("Executing test logic for test " + testNumber + "...");
        testCase.runTest();
    }
}
