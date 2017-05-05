package stack;

public interface StackInterface<T>

{
    // Throws StackUnderflowException if this stack is empty,
    // otherwise removes top element from this stack.
    void pop() throws StackUnderflowException;
  
  
    // Throws StackUnderflowException if this stack is empty,
    // otherwise returns top element from this stack.
    T top() throws StackUnderflowException;
  
  
    // Returns true if this stack is empty, otherwise returns false.
    boolean isEmpty();
 

}
