import java.util.HashMap;
import java.util.Map;

/**
 * Created by anykey on 14.05.16.
 */
class Command {
    private final Map<String, Cmd> cmdMap = new HashMap<>();
    private final static Command inst = new Command();

    private Command() {
        cmdMap.put("push", new Push());
        cmdMap.put("pop", new Pop());
        cmdMap.put("+", new Add());
        cmdMap.put("-", new Difference());
        cmdMap.put("*", new Multiply());
        cmdMap.put("/", new Divide());
        cmdMap.put("define", new Define());
        cmdMap.put("print", new Print());

    }


    Cmd getCommandByName(String commandName) {
        return cmdMap.get(commandName);
    }

    static Command getInst() {
        return inst;
    }
}
