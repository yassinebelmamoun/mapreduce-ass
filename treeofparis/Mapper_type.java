package belmamoun.malaval.sekkat.lab2.treeofparis;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import java.io.IOException;
import org.apache.hadoop.mapreduce.Mapper;


// Mapper:
    // Input  : [byteCount, row]
    // Output : [species, 1]

public class MapperType
extends Mapper<LongWritable, Text, Text, IntWritable> {
    private static Text tree_type = new Text();
    private final static IntWritable one = new IntWritable(1);
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String row    = value.toString().toLowerCase();
        String species = data[3]; 
        String[] data = row.split(";");
        tree_type.set(species);
        context.write(tree_type, one);
    }
}

