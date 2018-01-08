package belmamoun.malaval.sekkat.lab2.treeofparis;


import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import java.io.IOException;
import org.apache.hadoop.mapreduce.Mapper;


//Mapper:
    // Input  : [byteCount, row]
    // Output : [species_height, 1]


public class Mapper_height
extends Mapper<LongWritable, Text, Text, DoubleWritable> {

    private static Text tree_type = new Text();

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    	
        String row    = value.toString().toLowerCase();
        String[] data = row.split(";");        
        if(data[6] != null ) {
        	try {
        		Double height = Double.parseDouble(data[6]);       
                String species = data[3];
                tree_type.set(species);
                context.write(tree_type, new DoubleWritable(height));
        	} catch(Exception e) {
        		context.write(tree_type, new DoubleWritable(0));
        	}
        	
        }
        }
}

