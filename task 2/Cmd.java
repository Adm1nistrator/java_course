import java.util.Map;
import java.util.Stack;

/**
 * Created by anykey on 14.05.16.
 */
interface Cmd {

    void exec(Stack<Double> stack, Map<String, Double> define, String[] strings);

}
