package conversion;

import conversion.utils.ObjectStack;
import conversion.utils.SuperOutput;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple class to allow conversion from infix to postfix.
 *
 * @author Horizonistic
 * @version 2.2
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
    private String infix = "";
    private String postfix = "";
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

    /**
     * Constructor, requires SuperOutput to be able to print.
     *
     * @param so  SuperOutput for printing
     */
    public InfixToPostfix(SuperOutput so)
    {
        this.so = so;
    }

    /**
     * Converts infix expression to postfix.  This includes error-checking for unmatched parentheses and multiple consecutive operands and operators
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

                // If ending parenthesis, pop until opening is found
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
                else if (priority < this.operators.get(opStack.top())) // If incoming has higher priority than top of stack
                {
                    opStack.push(c);
                }
                else if (priority >= this.operators.get(opStack.top())) // If incoming has less or equal priority to top of stack
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
                // If multiple operands
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
     * Sets the infix expression to be used by this.toPostfix().
     *
     * @param input  The infix expression to set
     */
    public void setInfix(String input)
    {
        this.infix = input;
    }

    /**
     * Returns the postfix expression
     *
     * @return  The postfix expression
     */
    public String getPostfix()
    {
        return this.postfix;
    }

    /**
     * Builds and returns a map for keeping count of operators
     *
     * @return  The map for operator counting
     */
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