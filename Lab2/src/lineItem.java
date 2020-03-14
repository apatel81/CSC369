import java.awt.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class lineItem
{
    protected int id;
    protected int salesID;
    protected int productID;
    protected int quantity;

    public lineItem(int id, int salesID, int productID, int quantity)
    {
        this.id = id;
        this.salesID = salesID;
        this.productID = productID;
        this.quantity = quantity;
    }

//    public static sales createSale(Scanner in)
//    {
//        sales s = new sales(input_ssn, input_name, input_address, input_phoneNumber);
//
//        return s;
//    }

}
