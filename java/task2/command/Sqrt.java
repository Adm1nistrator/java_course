package task2.command;

import java.util.Map;
import java.util.Stack;

/**
 * Created by anykey on 16.05.16.
 */
public class Sqrt implements Command {
    @Override
    public void exec(Stack<Double> stack, Map<String, Double> variablesMap, String[] commandArgs) {
        if (commandArgs.length>0)
        {
            System.out.println("Для данной команды аргументы не требуются");
        }
        if (stack.size() == 0){
            System.out.println("Невозможно выполнить команду. Стек пуст!");
        }  else {
            stack.push(Math.sqrt(stack.lastElement()));
        }
    }
}
