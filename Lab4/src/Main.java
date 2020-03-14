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
        String csvFile = "outputProduct.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        Map<Double, String> sales_hashmap = new TreeMap<>();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                String[] data = line.split(cvsSplitBy);
                String id = data[0].trim();
                String product = data[1];
                Double price = -1 * Double.parseDouble(data[2]);
                String str_price = data[2];

                String totalString = id + "," + product + "," + str_price;
                sales_hashmap.put(price, totalString);

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

        BufferedWriter writer = new BufferedWriter(new FileWriter("Lab4Output.csv"));

        int count = 1;
        for (Map.Entry<Double, String> entry : sales_hashmap.entrySet())
        {
            if (count <= 10)
            {
//            System.out.println("Sales Date: " + entry.getKey() + " Total Sales: " + entry.getValue());
//            String s = entry.getKey().toString() + " " + entry.getValue().toString() + "\n";
                String s = entry.getValue().toString() + "\n";
                writer.write(s);
                count += 1;
            }

        }

        writer.close();
    }
}

