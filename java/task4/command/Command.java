package task4.command;

import java.util.Map;
import java.util.Stack;

/**
 * Created by anykey on 14.05.16.
 */
public interface Command {

    void exec(Stack<Double> stack, Map<String, Double> variablesMap, String[] commandArgs);

}
