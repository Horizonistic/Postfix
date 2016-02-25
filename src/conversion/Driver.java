package conversion;

import conversion.utils.ObjectStack;
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
            postfix.toPostfix(line);
        }
    }
}