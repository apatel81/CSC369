import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Mapper.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.Reducer.*;


public class DateTimeID implements Writable, WritableComparable<DateTimeID>
{
    private final Text date = new Text();
    private final Text timeID = new Text();

    public DateTimeID() {}

    public DateTimeID(String date, String timeID)
    {
        this.date.set(date);
        this.timeID.set(timeID);
    }

    @Override
    public void write(DataOutput out) throws IOException
    {
        date.write(out);
        timeID.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException
    {
        date.readFields(in);
        timeID.readFields(in);
    }

    @Override
    public int compareTo(DateTimeID pair)
    {
        if(date.compareTo(pair.getDate()) == 0)
        {
            return timeID.compareTo(pair.getTimeID());
        }
        return date.compareTo(pair.getDate());
    }

    public Text getDate()
    {
        return date;
    }

    public Text getTimeID()
    {
        return timeID;
    }
}
