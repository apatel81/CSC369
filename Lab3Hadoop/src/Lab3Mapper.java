import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Mapper.*;

public class Lab3Mapper extends Mapper<LongWritable, Text, DateTimeID, Text>
{
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {
        String valueAsString = value.toString().trim();
        String[] tokens = valueAsString.split(",");
        if (tokens.length != 5)
        {
            return;
        }
        context.write(new DateTimeID(tokens[1], tokens[2] + " " + tokens[0]), new Text(tokens[2] + " " + tokens[0]));
    }
}
