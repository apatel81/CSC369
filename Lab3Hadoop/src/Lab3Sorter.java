import java.io.*;
import java.util.Date;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Mapper.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.Reducer.*;

public class Lab3Sorter extends WritableComparator
{
    protected Lab3Sorter()
    {
        super(DateTimeID.class, true);
    }

    @Override
    public int compare(WritableComparable wc1, WritableComparable wc2)
    {
        DateTimeID pair = (DateTimeID) wc1;
        DateTimeID pair2 = (DateTimeID) wc2;
        return pair.compareTo(pair2);
    }
}
