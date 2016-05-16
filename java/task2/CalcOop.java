package task2;

import task2.command.Command;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by anykey on 14.05.16.
 */
class CalcOop {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner;
        String commandLine;
        Stack<Double> stack = new Stack<>();

      /*  BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"))*/

        if (args.length > 0) {
            scanner = new Scanner(new FileInputStream(args[0]));
        } else {
            scanner = new Scanner(System.in);
        }

        Map<String, Double> variablesMap = new HashMap<>();
        CommandRegistry registry = CommandRegistry.getInst();

        while (scanner.hasNextLine()) {
            commandLine = scanner.nextLine();
            String[] commandLineParts = commandLine.split(" ");

            if (commandLineParts.length == 0) {
                continue;
            }

            String commandName = commandLineParts[0];
            if (commandName.equals("exit"))
            {
                break;
            }

            Command command = registry.getCommandByName(commandName);


            String[] commandArgs = Arrays.copyOfRange(commandLineParts,1,commandLineParts.length);

            if (command != null) {
                command.exec(stack, variablesMap, commandArgs);
            } else {
                System.out.println("Команда не найдена");
            }


        }
    }

}
