package task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by anykey on 18.05.16.
 */
public class WordFrequency {
    public static void main(String[] args) throws IOException {
       /* BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();  */
        String fileName = "task3.txt";
        String[] words = new String[0];
        String input;
        Map<String, Integer> mapWordFrequency = new HashMap<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        while ((input = fileReader.readLine()) != null) {
            Pattern p = Pattern.compile("[^\\wа-яА-Я]+");
            words = p.split(input);
         /*   words = input.split(" ");*/
            int i = 1;
            mapWordFrequency.put(words[i], 1);
        }


        int count = 0;
        for (String word : words) {
            for (String otherWord : words) {
                if (otherWord.equals(word)) {
                    count++;
                }
            }
            mapWordFrequency.put(word, count);
            count = 0;
        }

        for (Map.Entry<String, Integer> entry : mapWordFrequency.entrySet()) {
            System.out.println("Слово: " + entry.getKey() + " Частота: " + entry.getValue() + " Частота: "+((100*entry.getValue())/words.length)+" %");
        }


    }


}
