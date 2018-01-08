package belmamoun.malaval.sekkat.lab2.tfidf;

 
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;


// Mapper 3:
    // Input  : [word--doc_name.txt, N;M]
    // Output : [word, doc_name;N;M;1]

public class Mapper_round_3
    extends Mapper<Text, Text, Text, Text> {
 
        private final static Text word      = new Text();
        private static       Text doc_name_N_M_1 = new Text();

        @Override
        public void map(Text key, Text value, Context context)
                throws IOException, InterruptedException {
 
            String[] word_doc_name = key.toString().split("--");
            word.set(word_doc_name[0]);
            String doc_name = word_doc_name[1];
            String[] N_M = value.toString().split(";");
            String N = N_M[0];
            String M = N_M[1];
            doc_name_N_M_1.set(doc_name +";"+ N +";"+ M +";"+ 1);
            context.write(word, doc_name_N_M_1);
        }
    }