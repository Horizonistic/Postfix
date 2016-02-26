package conversion.utils;

/**
 * Emulates a stack for objects.
 *
 * @author Richard Stegman
 * @version 1.0
 */
public class ObjectStack implements Stack
{
    private Object[] item;
    private int top;

    /**
     * Constructor, initiates stack with size of 1.
     */
    public ObjectStack() {
        item = new Object[1];
        top = -1;
    }

    /**
     * Returns if stack is empty or not.
     *
     * @return  True if empty, false if populated
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Returns if stack is full or not.
     *
     * @return  True if full, false if not
     */
    public boolean isFull() {
        return top == item.length-1;
    }

    /**
     * Clears the stack, deleting all data.
     */
    public void clear() {
        item = new Object[1];
        top = -1;
    }

    /**
     * Inserts passed onto the top of the stack.
     *
     * @param o  What to insert into stack
     */
    public void push(Object o) {
        if (isFull())
            resize(2 * item.length);
        item[++top] = o;
    }

    /**
     * Scales the stack down or up, allowing for efficient memory management,
     * meaning it's always between 25% and 75% full.
     *
     * @param size  How big to make the stack
     */
    private void resize(int size) {
        Object[] temp = new Object[size];
        for (int i = 0; i <= top; i++)
            temp[i] = item[i];
        item = temp;
    }

    /**
     * Returns the item on the top of the stack and removes it from the stack.
     *
     * @return  What was on the top
     */
    public Object pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            System.exit(1);
        }
        Object temp = item[top];
        item[top--] = null;
        if (top == item.length/4)
            resize(item.length/2);
        return temp;
    }

    /**
     * Returns the item on the top of the stack, but does not remove it from the stack.
     *
     * @return What is on the top
     */
    public Object top() {
        if (isEmpty()) {
            System.out.println("Stack Underflow2");
            System.exit(1);
        }
        return item[top];
    }
}