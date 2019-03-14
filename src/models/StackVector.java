package models;

import java.util.Vector;

public class StackVector<E> extends StackAC<E> {

    private Vector<E> vector;

    public StackVector() {
        vector = new Vector<>();
    }

    public StackVector(int i) {
        this.vector = new Vector<>(i);
    }

    public Vector<E> getVector() {
        return this.vector;
    }

    /**
     * pop si no se encuentra un push
     * @param item elemento
     */
    @Override
    public void push(E item) {
        //Will be popped next if no intervening push
        vector.add(item);
    }

    /**
     * EL elemento con push es eliminado
     * @return vector elimina
     */

    @Override
    public E pop() {
        //Most recently pushed item is removed and returned
        return vector.remove(size() -1);
    }

    /**
     * el valor es retornado
     * @return vectornpop
     */
    @Override
    public E peek() {
        //Top value (next to be popped) is returned
        return vector.get(size() -1);
    }

    /**
     * Valida si el stack esta vacio o no
     * @return true/false
     */
    @Override
    public boolean empty() {
        //Returns true if and only if the stack is empty
        return vector.isEmpty();
    }

    /**
     * Retorna la cantidad de elementos en el stack
     * @return tamanio
     */
    @Override
    public int size() {
        //Returns the number of elements in the stack
        return vector.size();
    }
}
