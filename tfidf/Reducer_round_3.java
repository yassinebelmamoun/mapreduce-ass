package belmamoun.malaval.sekkat.lab2.tfidf;


import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import java.util.HashMap;


// Reducer 3:
    // Input  : [word, [doc_name.txt;N;M;count, ...]]
    // Output : [word--doc_name.txt, tfidf]


public class Reducer_round_3
    extends Reducer<Text, Text, Text, DoubleWritable> {
 
        private static Text word_doc_name = new Text();
        private static DoubleWritable tfidf   = new DoubleWritable();
 
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

            String word = key.toString();
 
            HashMap<String, Integer> word_list_of_doc_name = new HashMap<String, Integer>();
            word_list_of_doc_name.clear();

            HashMap<String, Integer> list_of_doc_name = new HashMap<String, Integer>();
            list_of_doc_name.clear();

            HashMap<String, Integer> list_words = new HashMap<String, Integer>();
            list_words.clear();
 
 
            for (Text value: values) {
                String[] doc_name_N_M_count = value.toString().split(";");
                String  doc_name    = doc_name_N_M_count[0];
                Integer N      = Integer.parseInt(doc_name_N_M_count[1]);
                Integer M      = Integer.parseInt(doc_name_N_M_count[2]);
                Integer count  = Integer.parseInt(doc_name_N_M_count[3]);
 
                if (!list_of_doc_name.containsKey(doc_name)) {
                    list_of_doc_name.put(doc_name,N);
                } else {
                    if (M != list_of_doc_name.get(doc_name)) {
                        System.exit(-1);
                    }
                }
                 
                if (!word_list_of_doc_name.containsKey(word+"--"+doc_name)) {
                    word_list_of_doc_name.put(word+"--"+doc_name,N);
                } else {
                    if (N != word_list_of_doc_name.get(word+"--"+doc_name)) {
                        System.exit(-1);
                    }
                }
                 
                if (!list_words.containsKey(word)) {
                    list_words.put(word, count);
                } else {
                    Integer fq = list_words.get(word);
                    fq += count;
                    list_words.put(word, fq);
                }
            }


            for (String Worddoc_name: word_list_of_doc_name.keySet()) {
                Integer count_doc              = 2;
                String[] Word_doc_name = Worddoc_name.split("--");
                String Word       			= Word_doc_name[0];
                String doc_name       		= Word_doc_name[1];
                double occurence			= word_list_of_doc_name.get(Worddoc_name);
                double count_words_doc    	= list_of_doc_name.get(doc_name);
                Integer count_doc_with_word    = list_words.get(Word);
                word_doc_name.set(Worddoc_name);
                tfidf.set((occurence / count_words_doc) * (Math.log10(new Double(count_doc / count_doc_with_word))));
                context.write(word_doc_name,  tfidf);
            }
        }
    }