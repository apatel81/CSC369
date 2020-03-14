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

public class Lab3Partitioner extends Partitioner<DateTimeID, Text>
{
    @Override
    public int getPartition(DateTimeID pair, Text timeID, int numberOfPartitions)
    {
        return Math.abs(pair.getDate().hashCode() % numberOfPartitions);
    }

}

