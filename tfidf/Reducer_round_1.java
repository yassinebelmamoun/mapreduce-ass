package belmamoun.malaval.sekkat.lab2.tfidf;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import java.io.IOException;
import org.apache.hadoop.mapreduce.Reducer;


// Round 1 : Reducer 
    // Input  : [word--doc.txt, [])
    // Output : [word--doc.txt, N)


public class Reducer_round_1
    extends Reducer<Text, IntWritable, Text, IntWritable> {
 
        private static IntWritable sum_count = new IntWritable();

        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            int count = 0;
            for (IntWritable val : values) {
                count += val.get();
            }
            sum_count.set(count);
            context.write(key, sum_count);
        }
    }
