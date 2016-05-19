package task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by anykey on 18.05.16.
 */
public class WordFrequency {
    public static void main(String[] args) throws IOException {
       /* BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();  */
        String fileName = "task3.txt";
        String[] words = {};
        String input;
        Integer countWords=0;
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        while ((input = fileReader.readLine()) != null) {
            Pattern p = Pattern.compile("[^\\wа-яА-Я]+");
            words = p.split(input);
            Integer count;

            for (String word : words) {
                countWords++;
                count = wordFrequencyMap.get(word);
                wordFrequencyMap.put(word, count == null ? 1 : count + 1);

            }
        }

        List<Map.Entry<String, Integer>> wordFrequencyList = new ArrayList<Map.Entry<String, Integer>>(wordFrequencyMap.entrySet());
        System.out.println("\n==> Size of Entry list: " + wordFrequencyList.size());
        for (Map.Entry<String, Integer> temp : wordFrequencyList) {
            System.out.println(temp);
        }
       Collections.sort(wordFrequencyList, new Comparator<Map.Entry<String, Integer>>() {
           @Override
           public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            if (o2.getValue().equals(o1.getValue()))
            {

                  return o1.getKey().compareTo(o2.getKey());
               }
               else return o2.getValue().compareTo(o1.getValue());
           }
       });

        System.out.println("\n Sorted List " + wordFrequencyList.size());
        for (Map.Entry<String, Integer> temp : wordFrequencyList) {
            System.out.println(temp);
        }

/*

        System.out.println(countWords);



        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            System.out.println("Слово: " + entry.getKey() + " Частота: " + entry.getValue() + " Частота: " + ((100 * entry.getValue()) /countWords) + " %");
        }
*/


    }


}
