import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by anykey on 14.05.16.
 */
class CalcOop {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner;
        String s;
        Stack<Double> stack = new Stack<>();

      /*  BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"))*/

        if (args.length > 0) {
            scanner = new Scanner(new FileInputStream(args[0]));
        } else {
            scanner = new Scanner(System.in);
        }

        Map<String, Double> varMap = new HashMap<>();
        Commands singleton = Commands.getInst();

        while (scanner.hasNextLine()) {
            s = scanner.nextLine();
            String[] str = s.split(" ");
            Cmd kom = singleton.getCommandByName(str[0]);
            if (kom != null){
                try{
                    kom.exec(stack, varMap, str);
                }catch (NumberFormatException e){
                    System.out.println("Введена неизвестная команда");
                }
            } else {
                System.out.println("Команда не найдена");
            }

        }
    }

}
