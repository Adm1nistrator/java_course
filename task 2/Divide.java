import java.util.Map;
import java.util.Stack;

/**
 * Created by anykey on 16.05.16.
 */
class Divide implements Cmd {
    @Override
    public void exec(Stack<Double> stack, Map<String, Double> define, String[] strings) {
        if (stack.size() == 0){
            System.out.println("Невозможно выполнить команду. Стек пуст!");
        } else if (stack.size() == 1){
            System.out.println("Невозможно выполнить команду. Стек содержит один элемент!");
        } else {
            stack.push(stack.pop() / stack.pop());
        }
    }
}
