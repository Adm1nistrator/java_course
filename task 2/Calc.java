import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by anykey on 14.05.16.
 */
public class Calc {

    public static void main(String[] args) throws IOException {

      /*Reader fileReader = new InputStreamReader(new BufferedInputStream(new FileInputStream(args[0])));*/

        Stack<Double> stack = new Stack();

        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String string = reader.readLine();
            String[] commands = string.split(" ");
            String command = commands[0];

            if (command.equals("push")) {
                double value = new Integer(commands[1]);
                stack.push(value);

            } else if (command.equals("pop")) {
                stack.pop();

            } else if (command.equals("add")) {
                stack.push(stack.pop() + stack.pop());

            } else if (command.equals("sqrt")) {

                stack.push(Math.sqrt(stack.lastElement()));

            } else if (command.equals("print")) {
                System.out.println(stack.peek());
            } else if (command.equals("+")) {
                stack.push(stack.pop() + stack.pop());

            } else if (command.equals("-")) {
                stack.push(stack.pop() - stack.pop());

            } else if (command.equals("/")) {
                stack.push(stack.pop() / stack.pop());

            } else if (command.equals("*")) {
                stack.push(stack.pop() * stack.pop());

            } else if (command.equals("")) {
                break;
            }

        }

    }
}
