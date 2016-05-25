package task4.command;

import task4.In;
import task4.ResType;

import java.util.Stack;

/**
 * Created by anykey on 16.05.16.
 */
public class Multiply implements Command {
    @In(type = ResType.STACK)
    private Stack<Double> stack;
    @Override
    public void exec(String[] commandArgs) {
        if (commandArgs.length > 0) {
            System.out.println("Для данной команды аргументы не требуются");
        }
        if (stack.size() == 0) {
            System.out.println("Невозможно выполнить команду. Стек пуст!");
        } else if (stack.size() == 1) {
            System.out.println("Невозможно выполнить команду. Стек содержит один элемент!");
        } else {
            stack.push(stack.pop() * stack.pop());
        }
    }
}
