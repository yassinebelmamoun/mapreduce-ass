package belmamoun.malavalue.sekkat.lab2.treeofparis;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;


// Reducer:
    // Input  : [species, 1]
    // Output : [species, speciesCount]

public class ReducerType
    extends Reducer<Text, IntWritable, Text, IntWritable> {
        private static IntWritable sum = new IntWritable();
        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count = 0;
            for (IntWritable value : values) {
                count += value.get();
            }
            context.write(key, new IntWritable(count));
        }
    }
