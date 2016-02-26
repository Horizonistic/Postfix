package conversion;

import conversion.utils.ObjectStack;
import conversion.utils.SuperOutput;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A simple class to allow conversion from infix to postfix.  Handles all input and output with private methods.
 *
 * @author Horizonistic
 * @version 1.1
 */
public class InfixToPostfix
{
    private SuperOutput so;
    private Scanner in = new Scanner(System.in);
    private String infix;
    private String postfix;
    private Map<Character, Integer> operators = new HashMap<Character, Integer>()
    {{
        put('(', 1);
        put(')', 1);
        put('^', 2);
        put('*', 3);
        put('/', 3);
        put('-', 4);
        put('+', 4);
    }};

    public InfixToPostfix(SuperOutput superOutput)
    {
        this.so = superOutput;
    }

    /**
     * @return boolean  Whether or not it succeeded.
     */
    public boolean toPostfix(String input)
    {
        this.infix = input;
        //this.inputInfix();
        this.infixToPostfix();
        this.printPostfix();
        return true;
    }

    /**
     *
     */
    private void inputInfix()
    {
        do
        {
            this.so.println("Please enter an infix-formatted expression (enter \"skip\" to skip): ");
            if (this.in.hasNextLine())
            {
                String input = this.in.nextLine();
                if (!input.equalsIgnoreCase("skip"))
                {
                    if (input.replaceAll("[[0-9]\\s\\(\\)\\+\\-\\*\\/]", "").length() == 0)
                    {
                        this.so.printlnFile(input);
                        input = input.replaceAll("\\s", "");
                        this.infix = input;
                        return;
                    }
                    else
                    {
                        this.so.println("That contained invalid characters.  Enter \"skip\" to skip.");
                    }
                }
                else
                {
                    return;
                }
            }
            else
            {
                this.so.println("That was not a valid string.  Please try again or enter \"skip\" to skip.");
                this.in.nextLine();
            }
        } while (true);
    }

    /**
     *
     */
    private void infixToPostfix()
    {
        this.postfix = "";
        char c;
        int priority;
        ObjectStack opStack = new ObjectStack();
        this.infix = this.infix.replaceAll("[\\s]", "");

        for (int i = 0; i < this.infix.length(); i++)
        {
            c = this.infix.charAt(i);
            if (this.operators.containsKey(c))
            {
                priority = this.operators.get(c);

                if (c == ')')
                {
                    while (!opStack.isEmpty() && ((char) opStack.top() != '('))
                    {
                        this.postfix += opStack.pop();
                    }
                    if (!opStack.isEmpty() && ((char) opStack.top() == '('))
                    {
                        opStack.pop();
                    }
                }
                else if (opStack.isEmpty())
                {
                    opStack.push(c);
                }
                else if (priority < this.operators.get(opStack.top()))
                {
                    opStack.push(c);
                }
                else if (priority >= this.operators.get(opStack.top()))
                {
                    while (!opStack.isEmpty() && priority >= this.operators.get(opStack.top()) && ((char) opStack.top() != '('))
                    {
                        this.postfix += opStack.pop();
                    }
                    opStack.push(c);
                }
            }

            else if (Character.isDigit(c))
            {
                this.postfix += c;
            }
        }
        while (!opStack.isEmpty())
        {
            this.postfix += opStack.pop();
        }
    }

    /**
     *
     */
    private void printPostfix()
    {
        so.println(this.postfix);
    }

    /**
     *
     */
    public String getPostfix()
    {
        return this.postfix;
    }
}