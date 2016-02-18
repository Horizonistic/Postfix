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
        br = new BufferedReader(new FileReader("input.txt"));
        String line;
        while ((line = br.readLine()) != null)
        {
            postfix.toPostfix(line);
        }
        //postfix.toPostfix("3 * ( 5 * ( 5 - 2 ) ) - 9");
    }
}