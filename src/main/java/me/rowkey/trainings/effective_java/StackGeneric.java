package me.rowkey.trainings.effective_java;

import java.util.*;

public class StackGeneric<T> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private T[] elements;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public StackGeneric() {
        // �ɱ�֤�����ǰ�ȫ��
        elements = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    // ���ͷ���
    public static <T> Set<T> union(Set<? extends T> s1, Set<? extends T> s2) {
        Set<T> result = new HashSet<T>(s1);
        result.addAll(s2);

        return result;
    }

    public static void main(String args[]) {
        StackGeneric<String> stack = new StackGeneric<String>();
        for (String arg : args) {
            stack.push(arg);
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop().toUpperCase());
        }

        Set<Integer> integers = new HashSet<Integer>();
        integers.add(1);
        Set<Double> doubles = new HashSet<Double>();
        doubles.add(2.0);
        Set<Number> numbers = StackGeneric.<Number>union(integers, doubles);
    }

    public static <T extends Comparable<? super T>> T max(List<? extends T> list) {
        Iterator<? extends T> i = list.iterator();
        T result = i.next();
        while (i.hasNext()) {
            T t = i.next();
            if (t.compareTo(result) > 0)
                result = t;
        }

        return result;
    }

    public static void swap(List<?> list, int i, int j) {
        swapHelper(list, i, j);
    }

    public static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

    public void push(T e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public T pop() {
        if (size == 0)
            throw new EmptyStackException();
        T result = elements[--size];
        elements[size] = null;
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
