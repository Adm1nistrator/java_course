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
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        String input = "";
        ArrayList<String> words = new ArrayList<>();
        try {
            Reader fileInputStream = new InputStreamReader(new BufferedInputStream(new FileInputStream(inputFileName)));
            char current;
            while (fileInputStream.ready()) {
                current = (char) fileInputStream.read();

                if (Character.isLetterOrDigit(current)) {
                    input = input + Character.toString(current);
                } else {
                    if (!input.equals("")) {
                        words.add(input.toLowerCase());
                        input = "";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String word : words) {
            Integer count;
            count = wordFrequencyMap.get(word);
            wordFrequencyMap.put(word, count == null ? 1 : count + 1);
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

        PrintWriter printWriter = new PrintWriter(new File(outputFileName));
        Integer wordsCount=wordFrequencyList.size();
        String outString;
        outString = "Слово; Частота; Частота% ; Всего слов:"+wordsCount+"\n";
        printWriter.write(outString);

        for (Map.Entry<String, Integer> entry : wordFrequencyList) {

            double percentFrequency = (double) (entry.getValue()*100) / wordsCount;
            outString = String.format(entry.getKey() + ";" + entry.getValue() + ";" +"%1$+2.6f"+"%n",percentFrequency);
            printWriter.write(outString);
        }
        printWriter.close();
    }
}
