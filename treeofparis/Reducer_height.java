package belmamoun.malaval.sekkat.lab2.treeofparis;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.DoubleWritable;
import java.io.IOException;
import org.apache.hadoop.mapreduce.Reducer;


// Reducer:
    // Input  : [species, 1]
    // Output : [species, species_count]

public class Reducer_height
    extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
 
        private static DoubleWritable maximum_height = new DoubleWritable();
 
        @Override
        public void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
 
            Double maximum= 0.00;
            
            for (DoubleWritable value : values) {
                if(maximum < value.get()){
                	maximum = value.get();
                }
            }
            maximum_height.set(maximum);
            context.write(key, maximum_height);
        }
    }
