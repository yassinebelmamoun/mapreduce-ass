package belmamoun.malaval.sekkat.lab2.tfidf;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import java.util.HashMap;


// Reducer 2:
    // Input  : [doc_name, [word;N, word;N, word;N, word;N, word;N, (...)]]
    // Output : [word--doc_name, N;M]

public class Reducer_round_2
    extends Reducer<Text, Text, Text, Text> {
 
        private static Text doc_word_name = new Text();
        private static Text N_M       = new Text();
 
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context)
                throws IOException, InterruptedException {
            String doc_name = key.toString();
            int M = 0;
            HashMap<String, Integer> wordList = new HashMap<String, Integer>();
            wordList.clear();
            for (Text word_count: values) {
                String[] b = word_count.toString().split(";");
                int N = Integer.parseInt(b[1]);
                String word = b[0];
                wordList.put(word, N);
                M += N;
            }
 
            for (String word: wordList.keySet()) {
                doc_word_name.set(word+"--"+doc_name+".txt");
                N_M.set(wordList.get(word)+";"+M);
                context.write(doc_word_name,  N_M);
            }
        }
    }
