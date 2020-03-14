import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException {
        String csvFile = "outputSales.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        Map<String, String> sales_hashmap = new TreeMap<>();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                String[] data = line.split(cvsSplitBy);
                String date = data[1].trim();
                String id = data[0];
                String time = data[2].trim();

                String totalString = time + " " + id;

                if (sales_hashmap.containsKey(date))
                {
                    sales_hashmap.put(date, sales_hashmap.get(date) + ", " + totalString);
                }

                else
                {
                    sales_hashmap.put(date, totalString);
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("Lab3Output.csv"));


        for (Map.Entry<String, String> entry : sales_hashmap.entrySet())
        {
//            System.out.println("Sales Date: " + entry.getKey() + " Total Sales: " + entry.getValue());
            String s = entry.getKey().toString() + " " + entry.getValue().toString() + "\n";
            writer.write(s);

        }

        writer.close();
    }
}

