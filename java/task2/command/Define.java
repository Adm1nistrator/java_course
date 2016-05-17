package task2.command;

import java.util.Map;
import java.util.Stack;

/**
 * Created by anykey on 14.05.16.
 */
public class Define implements Command {
    @Override
    public void exec(Stack<Double> stack, Map<String, Double> variablesMap, String[] commandArgs) {

        if (commandArgs.length != 2) {
            System.out.println("Для выполенния данной операции необходимо 2 параметра");
            return;
        }

        try {
            variablesMap.put(commandArgs[0], Double.valueOf(commandArgs[1]));
        } catch (NumberFormatException e) {
            System.out.println("Значение параметра должно быть числовым");
        }
    }
}
