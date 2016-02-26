package conversion;

import conversion.utils.ObjectStack;
import conversion.utils.SuperOutput;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A simple class to allow conversion from infix to postfix.
 *
 * @author Horizonistic
 * @version 2.1
 */
public class InfixToPostfix
{
    // I just learned about enums in C++ and wanted use them.
    // Maybe unnecessary, but I think they're cool.
    private enum CharNames
    {
        NONE,
        OPERATOR,
        OPERAND
    }
    private SuperOutput so;
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
     *
     */
    public void toPostfix()
    {
        this.postfix = "";
        char c;
        int priority;

        ObjectStack opStack = new ObjectStack();
        Map<Character, Integer> charCount = this.buildMap();
        CharNames lastChar = CharNames.NONE;

        this.infix = this.infix.replaceAll("[\\s]", "");

        for (int i = 0; i < this.infix.length(); i++)
        {
            c = this.infix.charAt(i);
            if (this.operators.containsKey(c))
            {
                // If multiple operators
                if (c != '(' && c != ')')
                {
                    if (lastChar == CharNames.OPERATOR)
                    {
                        so.println("Invalid expression: multiple operators in a row");
                        this.postfix = "";
                        return;
                    }
                    else
                    {
                        lastChar = CharNames.OPERATOR;
                    }
                }

                priority = this.operators.get(c);
                charCount.put(c, charCount.get(c) + 1);

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
                if (lastChar == CharNames.OPERAND)
                {
                    so.println("Invalid expression: multiple operands in a row");
                    this.postfix = "";
                    return;
                }
                else
                {
                    lastChar = CharNames.OPERAND;
                }
                this.postfix += c;
            }
        }

        if (charCount.get('(') > charCount.get(')'))
        {
            so.println("Invalid expression: unmatched parenthesis.  Extra opening parenthesis.");
            this.postfix = "";
            return;
        }
        else if (charCount.get('(') < charCount.get(')'))
        {
            so.println("Invalid expression: unmatched parenthesis.  Extra closing parenthesis.");
            this.postfix = "";
            return;
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

    public void setInfix(String input)
    {
        this.infix = input;
    }

    /**
     *
     */
    public String getPostfix()
    {
        return this.postfix;
    }

    private Map buildMap()
    {
        Map<Character, Integer> charCount = new HashMap<Character, Integer>()
        {{
            put('(', 0);
            put(')', 0);
            put('^', 0);
            put('*', 0);
            put('/', 0);
            put('-', 0);
            put('+', 0);
        }};
        return charCount;
    }
}