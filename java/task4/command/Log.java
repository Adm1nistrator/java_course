package task4.command;

import task4.In;
import task4.ResType;

import java.util.Stack;

/**
 * Created by anykey on 21.05.16.
 */
public class Log implements Command {
    @In(type = ResType.STACK)
    private Stack<Double> stack;
    public void exec(String[] commandArgs) {
        if (stack.size() == 0){
            System.out.println("Невозможно выполнить команду. Стек пуст!");
        } else {
            stack.push(Math.log(stack.pop()));
        }
    }
}
