package task2.command;

import java.util.Map;
import java.util.Stack;

/**
 * Created by anykey on 16.05.16.
 */
public class Difference implements Command {
    public void exec(Stack<Double> stack, Map<String, Double> variablesMap, String[] commandArgs) {
        if (commandArgs.length>0)
        {
            System.out.println("Для данной команды аргументы не требуются");

        }

        if (stack.size() == 0){
            System.out.println("Невозможно выполнить команду. Стек пуст!");
        } else if (stack.size() == 1){
            System.out.println("Невозможно выполнить команду. Стек содержит один элемент!");
        } else {
            stack.push(stack.pop() - stack.pop());
        }
    }
}
