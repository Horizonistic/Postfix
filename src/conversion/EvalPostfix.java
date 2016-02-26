package conversion;

import conversion.utils.ObjectStack;
import conversion.utils.SuperOutput;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to take a postfix expression and evaluate it
 *
 * @author Horizonistic
 * @version 2.2
 */
public class EvalPostfix
{
    private SuperOutput so;
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

    /**
     * Constructor, requires SuperOutput to be able to print the eval.
     *
     * @param so  SuperOutput for printing
     */
    public EvalPostfix(SuperOutput so)
    {
        this.so = so;
    }

    /**
     * Uses a stack to evaluate a postfix expression.
     *
     * @param postfix  The postfix expression to evaluate
     */
    public void evalPostfix(String postfix)
    {
        this.postfix = postfix;
        this.eval = 0;
        int op1;
        int op2;
        char c;
        ObjectStack operands = new ObjectStack();

        for (int i = 0; i < this.postfix.length(); i++)
        {
            c = this.postfix.charAt(i);
            if (Character.isDigit(c))
            {
                operands.push(Character.getNumericValue(c));
            }
            else if (this.operators.containsKey(c))
            {
                op2 = (int) operands.pop();
                op1 = (int) operands.pop();
                switch (c)
                {
                    case '^':
                        operands.push((int) (Math.pow(op1, op2)));
                        break;

                    case '*':
                        operands.push(op1 * op2);
                        break;

                    case '/':
                        operands.push(op1 / op2);
                        break;

                    case '-':
                        operands.push(op1 - op2);
                        break;

                    case '+':
                        operands.push(op1 + op2);
                        break;

                    default:
                        so.println("WHAT");
                        break;
                }
            }
        }
        if (!operands.isEmpty())
        {
            this.eval = (int) operands.pop();
        }
    }

    /**
     * Returns the eval from the previous evaluation.
     *
     * @return  The eval
     */
    public int getEval()
    {
        return this.eval;
    }
}
