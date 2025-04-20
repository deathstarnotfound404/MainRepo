package proto;
import model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Field field = new Field();
        CommandHandler handler = new CommandHandler(field);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Prototípus elindult:");
        System.out.println("Segítségkérés: help");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Kilépés...");
                break;
            }
            handler.parseAndExecute(command);
        }
        scanner.close();
    }
}
