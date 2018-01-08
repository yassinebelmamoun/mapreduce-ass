package belmamoun.malaval.sekkat.lab2.tfidf;


import org.apache.hadoop.io.Text;

import java.io.IOException;
import org.apache.hadoop.mapreduce.Mapper;




// Mapper 2:
// Input  : [word--doc_name, N]
// Output : [doc_name, word;N]


public class Mapper_round_2
extends Mapper<Text, Text, Text, Text> {

    private static Text word_count = new Text();
    private final static Text doc_name = new Text();

    @Override
    public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        
        // Split by the "--" defined previously in Mapper 1
        String[] word_doc_name = key.toString().split("--");
        String word = word_doc_name[0];

        doc_name.set(word_doc_name[1]);

        String n = value.toString();
        word_count.set(word+";"+n);
        context.write(doc_name, word_count);
    }
}