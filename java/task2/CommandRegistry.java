package task2;

import task2.command.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anykey on 14.05.16.
 */
class CommandRegistry {
    private final Map<String, Command> cmdMap = new HashMap<>();
    private final static CommandRegistry inst = new CommandRegistry();

    private CommandRegistry() {
        cmdMap.put("push", new Push());
        cmdMap.put("pop", new Pop());
        cmdMap.put("+", new Add());
        cmdMap.put("-", new Difference());
        cmdMap.put("*", new Multiply());
        cmdMap.put("/", new Divide());
        cmdMap.put("sqrt", new Sqrt());
        cmdMap.put("define", new Define());
        cmdMap.put("print", new Print());

    }


    Command getCommandByName(String commandName) {
        return cmdMap.get(commandName);
    }

    static CommandRegistry getInst() {
        return inst;
    }
}
