package conversion.utils;

/**
 * Emulates a stack for integers.
 *
 * @author Richard Stegman
 * @author Horizonistic
 * @version 1.0
 */
public class IntegerStack
{
    private int[] item;
    private int top;

    public IntegerStack()
    {
        item = new int[1];
        top = -1;
    }

    public boolean isEmpty()
    {
        return top == -1;
    }

    public boolean isFull()
    {
        return top == item.length - 1;
    }

    public int getSize()
    {
        return top;
    }

    public void clear()
    {
        item = new int[1];
        top = -1;
    }

    public void push(int o)
    {
        if (isFull())
        {
            resize(2 * item.length);
        }
        item[++top] = o;
    }

    private void resize(int size)
    {
        int[] temp = new int[size];
        System.arraycopy(item, 0, temp, 0, top + 1);  // Faster than for loop, especially on larger arrays
        item = temp;
    }

    public int pop()
    {
        if (this.isEmpty())
        {
            System.err.println("Stack Underflow\n" + this);
            for (StackTraceElement err : Thread.currentThread().getStackTrace())
            {
                System.err.println(err);
            }
            System.exit(1);
        }
        int temp = item[top];
        item[top--] = 0;
        if (top == item.length / 4)
        {
            resize(item.length / 2);
        }
        return temp;
    }

    public int top()
    {
        if (this.isEmpty())
        {
            System.err.println("Stack Underflow2\n" + this);
            for (StackTraceElement err : Thread.currentThread().getStackTrace())
            {
                System.err.println(err);
            }
            System.exit(1);
        }
        return item[top];
    }

    public void dump()
    {
        for (int c : this.item)
        {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}