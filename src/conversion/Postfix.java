package conversion;

import conversion.utils.CharacterStack;
import conversion.utils.IntegerStack;
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
public class Postfix
{
    private SuperOutput so;
    private Scanner in = new Scanner(System.in);
    private String infix;
    private String postfix;
    private int eval;
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

    public Postfix(SuperOutput superOutput)
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
        this.outputPostfix();
        this.evalPost();
        return true;
    }

    /**
     *
     */
    private void inputInfix()
    {
        do
        {
            this.so.outputln("Please enter an infix-formatted expression (enter \"skip\" to skip): ");
            if (this.in.hasNextLine())
            {
                String input = this.in.nextLine();
                if (!input.equalsIgnoreCase("skip"))
                {
                    if (input.replaceAll("[[0-9]\\s\\(\\)\\+\\-\\*\\/]", "").length() == 0)
                    {
                        this.so.outputlnFile(input);
                        input = input.replaceAll("\\s", "");
                        this.infix = input;
                        return;
                    }
                    else
                    {
                        this.so.outputln("That contained invalid characters.  Enter \"skip\" to skip.");
                    }
                }
                else
                {
                    return;
                }
            }
            else
            {
                this.so.outputln("That was not a valid string.  Please try again or enter \"skip\" to skip.");
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
        CharacterStack opStack = new CharacterStack();
        this.infix = this.infix.replaceAll("[\\s]", "");

        for (int i = 0; i < this.infix.length(); i++)
        {
            c = this.infix.charAt(i);
            if (this.operators.containsKey(c))
            {
                priority = this.operators.get(c);

                if (c == ')')
                {
                    while (!opStack.isEmpty() && opStack.top() != '(')
                    {
                        this.postfix += opStack.pop();
                    }
                    if (!opStack.isEmpty() && opStack.top() == '(')
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
                    while (!opStack.isEmpty() && priority >= this.operators.get(opStack.top()) && opStack.top() != '(')
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

    public void evalPost()
    {
        this.eval = 0;
        int op1;
        int op2;
        char c;
        IntegerStack operands = new IntegerStack();

        for (int i = 0; i < this.postfix.length(); i++)
        {
            c = this.postfix.charAt(i);
            so.outputln(Character.toString(c));
            if (Character.isDigit(c))
            {
                operands.push(Character.getNumericValue(c));
            }
            else if (this.operators.containsKey(c))
            {
                so.outputln(String.valueOf(c));
                op2 = operands.pop();
                op1 = operands.pop();
                switch (c)
                {
                    case '^':
                        operands.dump();
                        operands.push((int) (Math.pow(op2, op1)));
                        operands.dump();
                        break;

                    case '*':
                        operands.dump();
                        operands.push(op2 * op1);
                        operands.dump();
                        break;

                    case '/':
                        operands.dump();
                        operands.push(op2 / op1);
                        operands.dump();
                        break;

                    case '-':
                        operands.dump();
                        operands.push(op2 - op1);
                        operands.dump();
                        break;

                    case '+':
                        operands.dump();
                        operands.push(op2 + op1);
                        operands.dump();
                        break;
                    default:
                        so.outputln("WHAT");
                        break;
                }
            }
            so.outputln();
        }
        this.eval = operands.pop();

        so.outputln(String.valueOf(this.eval));
        so.outputln("---------------------");
        so.outputln();
    }

    /**
     *
     */
    private void outputPostfix()
    {
        so.outputln(this.postfix);
    }
}