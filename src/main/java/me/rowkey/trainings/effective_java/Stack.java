/*
 * �����ڵĶ�������&&Clone
 */
package me.rowkey.trainings.effective_java;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private Object[] elements;
    private int size = 0;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null;//Eleminate obsolete reference

        return result;
    }

    //Ensure space for at least one more element
    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    @Override
    public Stack clone() {
        //�Կɱ�����������Ҫ���
        try {
            Stack result = (Stack) super.clone();
            result.elements = elements.clone();

            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
