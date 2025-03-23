import TestCases.ITestCase;
import java.util.*;
import TestCases.*;
import CallTracer.*;

public class Skeleton {
    public static final String WELCOME_MESSAGE = """
            Fungorium Skeleton TestProgram
            For help type help and press enter after the > mark.
            For exit type exit after the > mark.
            """;

    public static final String HELP_MESSAGE = """
            - Write prompts after the > mark.
            - ls prompt: The ls prompt lists all of the test cases with numbers. In order to run a specific test, use these numbers as arguments in the run test <test_number> prompt.
            - run test <test_number>: The run test prompt runs the test, marked with the <test_number> sequence.
            - exit: The exit prompt terminates the Fungorium Skeleton TestProgram.
            """;

    public static final Map<Integer, String> TEST_CASE_NAMES = new LinkedHashMap<>();
    public static final Map<Integer, ITestCase> TEST_CASE_CLASSES = new LinkedHashMap<>();
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
    static CallTracer callTracer = new CallTracer();
    static {
        TEST_CASE_CLASSES.put(1, new Test1(callTracer));
        TEST_CASE_CLASSES.put(2, new Test2(callTracer));
        TEST_CASE_CLASSES.put(3, new Test3(callTracer));
        TEST_CASE_CLASSES.put(6, new Test6(callTracer));
        TEST_CASE_CLASSES.put(9, new Test9(callTracer));
        TEST_CASE_CLASSES.put(10, new Test10(callTracer));
        TEST_CASE_CLASSES.put(11, new Test11(callTracer));
        TEST_CASE_CLASSES.put(13, new Test13(callTracer));
        TEST_CASE_CLASSES.put(14, new Test14(callTracer));
        TEST_CASE_CLASSES.put(15, new Test15(callTracer));
        TEST_CASE_CLASSES.put(16, new Test16(callTracer));
        TEST_CASE_CLASSES.put(17, new Test17(callTracer));
        TEST_CASE_CLASSES.put(18, new Test18(callTracer));
        TEST_CASE_CLASSES.put(19, new Test19(callTracer));
        TEST_CASE_CLASSES.put(20, new Test20(callTracer));
        TEST_CASE_CLASSES.put(21, new Test21(callTracer));
        TEST_CASE_CLASSES.put(22, new Test22(callTracer));
        TEST_CASE_CLASSES.put(23, new Test23(callTracer));
        TEST_CASE_CLASSES.put(24, new Test24(callTracer));
        TEST_CASE_CLASSES.put(25, new Test25(callTracer));
        TEST_CASE_CLASSES.put(26, new Test26(callTracer));
        TEST_CASE_CLASSES.put(27, new Test27(callTracer));
        TEST_CASE_CLASSES.put(28, new Test28(callTracer));
        TEST_CASE_CLASSES.put(29, new Test29(callTracer));
        TEST_CASE_CLASSES.put(30, new Test30(callTracer));
        TEST_CASE_CLASSES.put(31, new Test31(callTracer));
        TEST_CASE_CLASSES.put(32, new Test32(callTracer));
        TEST_CASE_CLASSES.put(33, new Test33(callTracer));
        TEST_CASE_CLASSES.put(34, new Test34(callTracer));
        TEST_CASE_CLASSES.put(36, new Test36(callTracer));
        TEST_CASE_CLASSES.put(37, new Test37(callTracer));
        TEST_CASE_CLASSES.put(38, new Test38(callTracer));
    }


    public static void main(String[] args) {
        //CallTracer callTracer = new CallTracer();
        Scanner scanner = new Scanner(System.in);
        System.out.println(WELCOME_MESSAGE);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("help")) {
                System.out.println(HELP_MESSAGE);
            } else if (input.equalsIgnoreCase("ls")) {
                listTests();
            } else if (input.equalsIgnoreCase("run test --all") || input.equalsIgnoreCase("run test -a")){
                System.out.println("#######################################################################################");
                System.out.println("Run all the tests");
                testDump(false);
            } else if (input.equalsIgnoreCase("run test --auto")) {
                System.out.println("#######################################################################################");
                System.out.println("Run all the tests automatically");
                testDump(true);
            } else if (input.startsWith("run test")) {
                System.out.println("------------------");
                runTest(input);
                System.out.println("------------------");
                System.out.println();
            } else if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting Fungorium Skeleton TestProgram...");
                break;
            } else {
                System.out.println("Invalid input, for help run the > help command");
            }
        }

        scanner.close();
    }

    private static void testDump(boolean auto) {
        int testNumber = 0;
        Scanner scanner = new Scanner(System.in); // Scanner inicializálása
        for (Map.Entry<Integer, String> entry : TEST_CASE_NAMES.entrySet()) {
            testNumber = entry.getKey();
            ITestCase testCase = TEST_CASE_CLASSES.get(testNumber);
            if (TEST_CASE_NAMES.containsKey(testNumber)) {
                if (auto) {
                    if (testNumber != 6 && testNumber != 13 && testNumber != 37) {      //Tesztek inputot várnak, nem automatizálhatók
                        System.out.println("---------------------------------[Test" + testNumber + "]---------------------------------");
                        System.out.println("Running test: " + TEST_CASE_NAMES.get(testNumber));
                        executeTest(testNumber, testCase);
                        System.out.println();
                    }
                } else {
                    System.out.println("---------------------------------[Test" + testNumber + "]---------------------------------");
                    System.out.println("Running test: " + TEST_CASE_NAMES.get(testNumber));
                    executeTest(testNumber, testCase);
                    System.out.println();
                }

            } else {
                System.out.println("Test" + testNumber + " not found.");
            }

            String input;
            if (!auto) {
                while (true) {
                    System.out.println("Press 'n' to continue or 'q' to quit:");
                    System.out.print("> ");
                    input = scanner.nextLine().trim().toLowerCase(); // Kisbetűsítés és felesleges szóközök eltávolítása

                    if (input.equals("n")) {
                        break; // Folytatja a ciklust
                    } else if (input.equals("q")) {
                        System.out.println("Exiting execution dump.");
                        System.out.println("#######################################################################################");
                        return; // Kilép a függvényből
                    } else {
                        System.out.println("Invalid input. Please enter 'n' to continue or 'q' to quit.");
                    }
                }
            }
        }

        System.out.println("All tests completed.");
        System.out.println("#######################################################################################");
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
                System.out.println("Running test " + testNumber + ": " + TEST_CASE_NAMES.get(testNumber));
                executeTest(testNumber, testCase);
            } else {
                System.out.println("Test not found. Use 'ls' to list available tests.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid test number format. Use an integer value.");
        }
    }

    private static void executeTest(int testNumber, ITestCase testCase) {
        System.out.println();
        testCase.runTest();
    }
}
