import java.awt.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class sales
{
    protected int id;
    protected String date;
    protected String time;
    protected int storeID;
    protected int customerID;
//    protected static int id = 0;

    public sales(int id, String date, String time, int storeID, int customerID)
    {
        this.id = id;
        this.date = date;
        this.time = time;
        this.storeID = storeID;
        this.customerID = customerID;
    }

//    public static sales createSale(Scanner in)
//    {
//        sales s = new sales(input_ssn, input_name, input_address, input_phoneNumber);
//
//        return s;
//    }

}
