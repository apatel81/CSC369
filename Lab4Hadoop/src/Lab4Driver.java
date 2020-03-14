import org.apache.log4j.Logger;
import org.apache.hadoop.util.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.mapreduce.lib.input.*;

//import javax.tools.Tool;


public class Lab4Driver extends Configured implements Tool
{
    private static final Logger THE_LOGGER = Logger.getLogger(Lab4Driver.class);

    @Override
    public int run(String[] args) throws Exception
    {
        Job job = Job.getInstance();
        job.getConfiguration().setInt("N", n);
        job.setJarByClass(Lab4Driver.class);
        job.setJobName("Lab4Driver");
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Text.class);
        job.setMapperClass(Lab4Mapper.class);
//        job.setCombinerClass(Lab3Combiner.class);
//        job.setPartitionerClass(Lab3Partitioner.class);
//        job.setGroupingComparatorClass(Lab3Grouper.class);
//        job.setSortComparatorClass(Lab3Sorter.class);
        job.setReducerClass(Lab4Reducer.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        boolean status = job.waitForCompletion(true);
        THE_LOGGER.info("run(): status=" + status);
        return status ? 0 : 1;
    }

    public static void main(String[] args) throws Exception
    {
        if (args.length != 2)
        {
            throw new IllegalArgumentException
                    ("usage: <input> <output>");
        }

        THE_LOGGER.info("inputDir = " + args[0]);
        THE_LOGGER.info("outputDir = " + args[1]);
        int returnStatus = ToolRunner.run(new Lab4Driver(), args);
        THE_LOGGER.info("returnStatus=" + returnStatus);
        System.exit(returnStatus);
    }

}