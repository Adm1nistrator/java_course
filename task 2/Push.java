import java.util.Map;
import java.util.Stack;

/**
 * Created by anykey on 14.05.16.
 */
class Push implements Cmd {
    private Stack<Double> stack;
    public void exec(Stack<Double> stack, Map<String, Double> define, String[] strings) {
        if (define.containsKey(strings[1])) {
            stack.push(define.get(strings[1]));
        } else {
            stack.push(Double.valueOf(strings[1]));
        }
    }
}
