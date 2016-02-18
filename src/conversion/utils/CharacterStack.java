package conversion.utils;

/**
 * Emulates a stack for characters.
 *
 * @author Richard Stegman
 * @author Horizonistic
 * @version 1.0
 */
public class CharacterStack
{
    private Character[] item;
    private int top;

    public CharacterStack()
    {
        item = new Character[1];
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
        item = new Character[1];
        top = -1;
    }

    public void push(Character o)
    {
        if (isFull())
        {
            resize(2 * item.length);
        }
        item[++top] = o;
    }

    private void resize(int size)
    {
        Character[] temp = new Character[size];
        System.arraycopy(item, 0, temp, 0, top + 1);  // Faster than for loop, especially on larger arrays
        item = temp;
    }

    public Character pop()
    {
        if (isEmpty())
        {
            System.err.println("Stack Underflow\n" + this);
            for (StackTraceElement err : Thread.currentThread().getStackTrace())
            {
                System.err.println(err);
            }
            System.exit(1);
        }
        Character temp = item[top];
        item[top--] = null;
        if (top == item.length / 4)
        {
            resize(item.length / 2);
        }
        return temp;
    }

    public Character top()
    {
        if (isEmpty())
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
        for (Character c : this.item)
        {
            System.out.print(c);
        }
    }
}