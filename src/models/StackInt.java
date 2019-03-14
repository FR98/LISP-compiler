package models;

public interface StackInt<E> {

    void push(E item);
    // pre:
    // post: item is added to stack
    // will be popped next if no intervening push

    E pop();
    // pre: stack is not empty
    // post: most recently pushed item is removed and returned

    E peek();
    // pre: stack is not empty
    // post: top value (next to be popped) is returned

    boolean empty();
    // post: returns true if and only if the stack is empty

    int size();
    // post: returns the number of elements in the stack

}
