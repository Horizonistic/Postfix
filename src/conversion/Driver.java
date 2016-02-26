package conversion;

import conversion.utils.SuperOutput;

import java.io.*;

/**
 * Reads an input file line-by-line and converts each infix expression to postfix,
 * checking for syntax errors, and then evaluates the postfix expression.
 *
 * @author Horizonistic
 * @version 2.2
 */
public class Driver
{
    public static void main(String[] args) throws IOException
    {
        SuperOutput so = new SuperOutput("csis.txt");
        InfixToPostfix postfix = new InfixToPostfix(so);
        EvalPostfix eval = new EvalPostfix(so);

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

        String line;
        while ((line = br.readLine()) != null)
        {
            so.println(line);
            postfix.setInfix(line);
            postfix.toPostfix();
            so.println(postfix.getPostfix());

            if (postfix.getPostfix().equals(""))
            {
                eval.evalPostfix(postfix.getPostfix());
                so.println(eval.getEval());
            }
            so.println();
        }

        so.close();
    }
}