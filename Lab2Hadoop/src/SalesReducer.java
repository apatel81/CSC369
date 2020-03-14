import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Reducer.*;

public class SalesReducer extends Reducer<Text, LongWritable, Text, LongWritable>
{
    //@Override
    public void reduce(Text date, Iterable<LongWritable> sales, Context context) throws IOException, InterruptedException
    {
        Long sum = 0L;
        for(LongWritable sale: sales)
        {
            sum += 1L;
        }
        context.write(date, new LongWritable(sum));
    }
}