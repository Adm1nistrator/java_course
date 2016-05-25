package task4.command;

import task4.In;
import task4.ResType;

import java.util.Stack;

/**
 * Created by anykey on 16.05.16.
 */
public class Sqrt implements Command {
    @In(type = ResType.STACK)
    private Stack<Double> stack;
    @Override
    public void exec(String[] commandArgs) {
        if (commandArgs.length > 0) {
            System.out.println("Для данной команды аргументы не требуются");
        }
        if (stack.size() == 0) {
            System.out.println("Невозможно выполнить команду. Стек пуст!");
        } else {
            stack.push(Math.sqrt(stack.pop()));
        }
    }
}
