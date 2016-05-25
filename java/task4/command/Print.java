package task4.command;

import task4.In;
import task4.ResType;

import java.util.Stack;

/**
 * Created by anykey on 14.05.16.
 */
public class Print implements Command {
    @In(type = ResType.STACK)
    private Stack<Double> stack;
    public void exec(String[] commandArgs) {
        if (commandArgs.length > 0) {
            System.out.println("Для данной команды аргументы не требуются");
        }
        if (stack.size() == 0) {
            System.out.println("Невозможно выполнить команду. Стек пуст!");
        } else {
            System.out.println(stack.lastElement());
        }
    }
}
