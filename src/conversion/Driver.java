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
        Postfix postfix = new Postfix(new SuperOutput("csis.txt"));

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
        String line;
        while ((line = br.readLine()) != null)
        {
            postfix.toPostfix(line);
        }
    }
}