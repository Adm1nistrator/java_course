import java.util.Map;
import java.util.Stack;

/**
 * Created by anykey on 14.05.16.
 */
class Add implements Cmd {

    private Stack<Double> stack;
    public void exec(Stack<Double> stack, Map<String, Double> define, String[] strings) {
        if (stack.size() == 0){
            System.out.println("Невозможно выполнить команду. Стек пуст!");
        } else if (stack.size() == 1){
            System.out.println("Невозможно выполнить команду. Стек содержит один элемент!");
        } else {
            stack.push(stack.pop() + stack.pop());
        }
    }
}
