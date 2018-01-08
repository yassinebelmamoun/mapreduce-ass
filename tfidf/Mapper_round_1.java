package belmamoun.malaval.sekkat.lab2.tfidf;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


//	Mapper round 1
//  output : [word--doc.txt, 1]

public class Mapper_round_1
    extends Mapper<LongWritable, Text, Text, IntWritable> {
 
        private static       Text   doc_word = new Text();
        private final static IntWritable one = new IntWritable(1);
 
        @Override
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
 
            String line    = value.toString().toLowerCase();
            String[] words = line.split("\\W+");

            for (int i=0; i < words.length; i++) {
                String word = words[i];
                // We do not bother with words with less than 3 characters
                // Remove words with 1 or 2 characters (i.e. "an", "a", "if")
                if (word.length() <= 2)
                     {
                    continue;
                }
                String doc_name = ((FileSplit) context.getInputSplit()).getPath().getName();
                doc_word.set(word+"--"+doc_name+".txt");
                context.write(doc_word, one);
            }
        }
    }