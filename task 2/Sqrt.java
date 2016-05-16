import java.util.Map;
import java.util.Stack;

/**
 * Created by anykey on 16.05.16.
 */
class Sqrt implements Cmd {
    @Override
    public void exec(Stack<Double> stack, Map<String, Double> define, String[] strings) {
        if (stack.size() == 0){
            System.out.println("Невозможно выполнить команду. Стек пуст!");
        }  else {
            stack.push(Math.sqrt(stack.lastElement()));
        }
    }
}
