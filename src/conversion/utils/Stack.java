package conversion.utils;

public interface Stack
{
    boolean isEmpty();
    boolean isFull();
    void clear();
    void push(Object o);
    Object pop();
    Object top();
}
