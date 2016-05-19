package task3;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by anykey on 18.05.16.
 */
public class WordFrequency {
    public static void main(String[] args) throws IOException {
       /* BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputFileName = bufferedReader.readLine();  */
        String inputFileName = "big.txt";
        String outputFileName = "WordFrequency.csv";
        String[] words = {};
        String input;
        Integer countWords = 0;
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(inputFileName));
        //читаем фаил по строкам и парсим в map
        while ((input = fileReader.readLine()) != null) {
            if (input.equals(""))
            {
                continue;
            }
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

        //сортировка
        Collections.sort(wordFrequencyList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o2.getValue().equals(o1.getValue())) {

                    return o1.getKey().compareTo(o2.getKey());
                } else return o2.getValue().compareTo(o1.getValue());
            }
        });


        PrintWriter PrintWriter = new PrintWriter(new File(outputFileName));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Слово");
        stringBuilder.append(',');
        stringBuilder.append("Частота");
        stringBuilder.append(',');
        stringBuilder.append("Частота % ");
        stringBuilder.append(',');
        stringBuilder.append('\n');

        for (Map.Entry<String, Integer> entry : wordFrequencyList) {
            stringBuilder.append(entry.getKey());
            stringBuilder.append(',');
            stringBuilder.append(entry.getValue());
            stringBuilder.append(',');
            stringBuilder.append(100*entry.getValue()/countWords);
            stringBuilder.append('\n');
        }

        PrintWriter.write(stringBuilder.toString());
        PrintWriter.close();




    }


}
