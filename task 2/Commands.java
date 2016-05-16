import java.util.HashMap;
import java.util.Map;

/**
 * Created by anykey on 14.05.16.
 */
class Commands {
    private final Map<String, Cmd> cmdMap = new HashMap<>();
    private final static Commands inst = new Commands();

    private Commands() {
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


    Cmd getCommandByName(String commandName) {
        return cmdMap.get(commandName);
    }

    static Commands getInst() {
        return inst;
    }
}
