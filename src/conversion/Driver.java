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
            br = new BufferedReader(new FileReader("infix.txt"));
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File \"infix.txt\" not found or cannot be opened.");
            return;
        }

        SuperOutput so = new SuperOutput("csis.txt");
        InfixToPostfix postfix = new InfixToPostfix(so);
        EvalPostfix eval = new EvalPostfix(so);

        String line;
        while ((line = br.readLine()) != null)
        {
            postfix.toPostfix(line);
            eval.evalPostfix(postfix.getPostfix());
            eval.printEval();
        }
    }
}