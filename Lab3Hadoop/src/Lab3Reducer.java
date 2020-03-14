import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Reducer.*;

public class Lab3Reducer extends Reducer<DateTimeID, Text, Text, Text>
{
    //@Override
    public void reduce(DateTimeID key, Iterable<Text> values, Context context) throws IOException, InterruptedException
    {
        String result = "";
        for(Text value: values)
        {
            result += value + ", ";
        }
        result = result.substring(0, result.length()-2);

        context.write(key.getDate(), new Text(result));
    }
}