package task4.command;

import task4.Param;
import task4.ResType;

import java.util.Map;
import java.util.Stack;

/**
 * Created by anykey on 14.05.16.
 */
public class Push implements Command {
    @Param(type = ResType.STACK)
    private Stack<Double> stack;
    public void exec(Stack<Double> stack, Map<String, Double> variablesMap, String[] commandArgs) {

        if (commandArgs.length != 1) {
            System.out.println("Для выполенния данной операции необходим 1 аргумент");
            return;
        }

        String arg = commandArgs[0];

        if (variablesMap.containsKey(arg)) {
            stack.push(variablesMap.get(arg));
        } else {
            try {
                stack.push(Double.valueOf(arg));
            } catch (NumberFormatException e) {
                System.out.println("Значение аргумета должно быть числовым");
            }
        }
    }
}
