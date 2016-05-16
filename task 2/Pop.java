import java.util.Map;
import java.util.Stack;

/**
 * Created by anykey on 14.05.16.
 */
class Pop implements Cmd {
    @Override
    public void exec(Stack<Double> stack, Map<String, Double> define, String[] strings) {
        if (stack.size() == 0){
            System.out.println("Невозможно выполнить команду. Стек пуст!");
        }  else {
            stack.pop();
        }

    }
}
