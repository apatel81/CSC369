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


public class Record implements Comparable<Record>
{
    private int id;
    private String product;
    private double price;

    public Record() {}

    public Record(int id, String product, Double price)
    {
        this.id = id;
        this.product = product;
        this.price = price;
    }

    public String toString()
    {
        return id + "," + product + "," + price;
    }

    public int compareTo(Record other)
    {
        if (this.price > other.price)
        {
            return -1;
        }

        if (this.price < other.price)
        {
            return 1;
        }

        if (this.id > other.id)
        {
            return 1;
        }

        if (this.id < other.id)
        {
            return -1;
        }

        return 0;

    }

}
