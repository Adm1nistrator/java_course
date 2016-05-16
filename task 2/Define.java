import java.util.Map;
import java.util.Stack;

/**
 * Created by anykey on 14.05.16.
 */
class Define implements Cmd {
    @Override
    public void exec(Stack<Double> stack, Map<String, Double> define, String[] strings) {

        try{
            define.put(strings[1], Double.valueOf(strings[2]));
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Недостаточно параметров для выполнения операции");
        }
    }
}
