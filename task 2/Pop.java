import java.util.Map;
import java.util.Stack;

/**
 * Created by anykey on 14.05.16.
 */
class Pop implements Cmd {
    private Stack<Double> stack;
    @Override
    public void exec(Stack<Double> stack, Map<String, Double> define, String[] strings) {
        stack.pop();

    }
}
