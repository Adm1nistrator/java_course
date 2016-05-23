package task4;

import task4.command.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by anykey on 14.05.16.
 */
class CommandFactory {
    private final Map<String, Command> cmdMap = new HashMap<>();
    private final static CommandFactory inst = new CommandFactory();

    private CommandFactory(){
        Properties properties = new Properties();

        try(InputStream inputStream = CommandFactory.class.getResourceAsStream("commands.properties")){
            properties.load(inputStream);
        }catch (IOException e){
            System.out.println("Чтение файла commands.properties завершилось с ошибкой!");
        }

        for (String key : properties.stringPropertyNames()){
            properties.get(key);
            try{
                Class cl = Class.forName(properties.getProperty(key));
                cmdMap.put(key, (Command) cl.newInstance());
            } catch (Exception e) {
                System.out.println("Не удалось прочитать класс: " + key );
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
