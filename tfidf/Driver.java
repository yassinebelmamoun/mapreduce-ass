package belmamoun.malaval.sekkat.lab2.tfidf;
 
import java.util.Arrays;
import org.apache.hadoop.fs.FileSystem;  

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration; 

 import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
 
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.ToolRunner;





public class Driver extends Configured implements Tool{

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new Driver(), args);
        System.exit(res);
    }
 
    @Override
    public int run(String[] args) throws Exception { 
        Configuration config = new Configuration();
        // First Driver
            // Output: [word--doc.txt; N]
            // N = Occurence in each txt file

        Job round1 = Job.getInstance(config);
        round1.setJobName("TFIDF_round_1");
        round1.setJarByClass(Driver.class);
        round1.setMapOutputKeyClass(Text.class);
        round1.setMapOutputValueClass(IntWritable.class);
        round1.setOutputKeyClass(Text.class);
        round1.setOutputValueClass(IntWritable.class);
        round1.setMapperClass(Mapper_round_1.class);
        round1.setReducerClass(Reducer_round_1.class);
        round1.setInputFormatClass(TextInputFormat.class);
        round1.setOutputFormatClass(TextOutputFormat.class);
 
        // Set Mapper/Reducer directory:
        config.set("originalInputDir", args[0]);
        config.set("inputDir1", args[0]);
        config.set("inputDir2", args[1]);
        config.set("outputDir", round1.getJobName());
 
        FileInputFormat.addInputPath(round1, new Path(config.get("inputDir1")));
        FileInputFormat.addInputPath(round1, new Path(config.get("inputDir2")));
        FileSystem filesys_round_1 = FileSystem.get(config);
        if (filesys_round_1.exists(new Path(config.get("outputDir"))))
            filesys_round_1.delete(new Path(config.get("outputDir")), true);
        FileOutputFormat.setOutputPath(round1, new Path(config.get("outputDir")));

        round1.waitForCompletion(true);
 
        // Second Driver

            // Input:  [word--doc.txt; N]
            // Output: [word--doc.txt; N; M]

            // N = Occurence of a word / file
            // M = Count of words / file
 
        config.set("inputDir", config.get("outputDir")); 
        Job round2 = Job.getInstance(config);
        round2.setJobName("TFIDF_round_2");
        round2.setJarByClass(Driver.class);
        // Mapper/Reducer class outputs
        round2.setMapOutputKeyClass(Text.class);
        round2.setMapOutputValueClass(Text.class);
        round2.setOutputKeyClass(Text.class);
        round2.setOutputValueClass(Text.class);
        // Mapper/Reducer Class
        round2.setMapperClass(Mapper_round_2.class);
        round2.setReducerClass(Reducer_round_2.class);
        config.set("key.value.separator.in.input.line", "\t");
        round2.setInputFormatClass(KeyValueTextInputFormat.class);
        round2.setOutputFormatClass(TextOutputFormat.class);
        // Paths
        config.set("outputDir", round2.getJobName());
 
        FileInputFormat.addInputPath(round2, new Path(config.get("inputDir")));        
        
        FileSystem filesys_round_2 = FileSystem.get(config);
        if (filesys_round_2.exists(new Path(config.get("outputDir"))))
            filesys_round_2.delete(new Path(config.get("outputDir")), true);
        FileOutputFormat.setOutputPath(round2, new Path(config.get("outputDir")));
  
        // Third Driver:

            // Input:  [word--doc.txt; N; M]
            // Output: [word--doc.txt; N; M; fq]

            // N: Occurence of a word / file
            // M: Count of words / file
            // fq = word's frequency
 
        config.set("inputDir", config.get("outputDir")); 
        Job round3 = Job.getInstance(config);
        round3.setJobName("TFIDF_round_3");
        round3.setJarByClass(Driver.class);
 
        // Mapper/Reducer class outputs
        round3.setMapOutputKeyClass(Text.class);
        round3.setMapOutputValueClass(Text.class);
        // Mapper/Reducer Class
        round3.setOutputKeyClass(Text.class);
        round3.setOutputValueClass(Text.class);
        round3.setMapperClass(Mapper_round_3.class);
        round3.setReducerClass(Reducer_round_3.class);
        config.set("key.value.separator.in.input.line", "\t");
        round3.setInputFormatClass(KeyValueTextInputFormat.class);
        round3.setOutputFormatClass(TextOutputFormat.class);
        // Paths
        config.set("outputDir", round3.getJobName());
 
        FileInputFormat.addInputPath(round3, new Path(config.get("inputDir")));    
        FileSystem filesys_round_3 = FileSystem.get(config);
        if (filesys_round_3.exists(new Path(config.get("outputDir"))))
            filesys_round_3.delete(new Path(config.get("outputDir")), true);
        FileOutputFormat.setOutputPath(round3, new Path(config.get("outputDir")));
 
        return 0;
    }
}