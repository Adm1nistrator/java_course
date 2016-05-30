package task4;

import task4.command.Command;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by anykey on 14.05.16.
 */
class CommandFactory {
    private final Map<String, Command> cmdMap = new HashMap<>();
    private final static CommandFactory inst = new CommandFactory();
    private Stack<Double> stack = new Stack<>();

   private Map<String, Double> variablesMap = new HashMap<>();

    private CommandFactory() {

        Properties properties = new Properties();

        try (InputStream inputStream = CommandFactory.class.getResourceAsStream("commands.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Чтение файла commands.properties завершилось с ошибкой!");
        }

        /*    Class myClass = Class.forName("task4.command.Add");
        System.out.println(myClass);
        Field field = myClass.getField("stack");
        System.out.println(field);
        In anno = field.getAnnotation(In.class);
        System.out.print(anno);*/

        for (String key : properties.stringPropertyNames()) {
            properties.get(key);
            try {
                Class cl = Class.forName(properties.getProperty(key));
                Command command = (Command) cl.newInstance();

                List<Field> fields = Arrays.asList(cl.getDeclaredFields());
                for (Field field : fields) {
                    if (field.isAnnotationPresent(In.class)) {
                        In annotation = field.getAnnotation(In.class);
                        annotation.type();
                        switch (annotation.type()) {
                            case STACK:
                                field.setAccessible(true);
                                field.set(command, stack);
                                break;
                            case CONTEXT:
                                field.setAccessible(true);
                                field.set(command, variablesMap);
                                break;
                        }
                    }
                }
                cmdMap.put(key, command);
            } catch (Exception e) {
                System.out.println("Не удалось прочитать класс: " + key);
            }
        }
    }


    Command getCommandByName(String commandName) {
        return cmdMap.get(commandName);
    }

    static CommandFactory getInst() {
        return inst;
    }
}
