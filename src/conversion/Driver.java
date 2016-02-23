package conversion;

import conversion.utils.SuperOutput;

import java.io.*;

/**
 * @author Horizonistic
 */
public class Driver
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br;
        try
        {
            br = new BufferedReader(new FileReader("input.txt"));
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File \"input.txt\" not found or cannot be opened.");
            return;
        }

        SuperOutput so = new SuperOutput("csis.txt");
        Postfix postfix = new Postfix(so);

        String line;
        while ((line = br.readLine()) != null)
        {
            //so.print(1.0001 + 100, "test");
            //postfix.toPostfix(line);
        }

        postfix.toPostfix("12^3*4-56/78+/+");
    }
}