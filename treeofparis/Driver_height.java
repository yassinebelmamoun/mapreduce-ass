package belmamoun.malaval.sekkat.lab2.treeofparis;
 

import java.util.Arrays;

import org.apache.hadoop.fs.FileSystem;  
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.conf.Configuration; 

import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.util.Tool;
import org.apache.hadoop.conf.Configured;

import org.apache.hadoop.util.ToolRunner;


public class Driver_height extends Configured implements Tool{
    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new Driver_height(), args);
        System.exit(res);
    }
 
    @Override
    public int run(String[] args) throws Exception { 
        Configuration config = new Configuration();
 
 
        // First Driver:
            // Input  [byteCount, line]
            // Output [species, species_occ]
 
        Job round = Job.getInstance(config);
        round.setJobName("Max_height");
        round.setJarByClass(Driver_type.class);
        round.setMapOutputKeyClass(Text.class);
        round.setMapOutputValueClass(DoubleWritable.class);
        round.setOutputKeyClass(Text.class);
        round.setOutputValueClass(DoubleWritable.class);
        round.setMapperClass(Mapper_height.class);
        round.setReducerClass(Reducer_height.class);
        round.setInputFormatClass(TextInputFormat.class);
        round.setOutputFormatClass(TextOutputFormat.class);
        config.set("inputDir", args[0]);
        config.set("outputDir", round.getJobName());
        config.set("originalInputDir", args[0]);
        FileInputFormat.addInputPath(round, new Path(config.get("inputDir")));
        FileSystem filesys = FileSystem.get(config);
        if (filesys.exists(new Path(config.get("outputDir"))))
            filesys.delete(new Path(config.get("outputDir")), true);
        FileOutputFormat.setOutputPath(round, new Path(config.get("outputDir")));
        round.waitForCompletion(true);
        return 0;
    }
}