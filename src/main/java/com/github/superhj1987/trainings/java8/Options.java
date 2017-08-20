package com.github.superhj1987.trainings.java8;

import java.util.Optional;
import java.util.function.BiFunction;

/**
 * Created by Bryant.Hang on 2017/8/18.
 */
public interface Options {
    static <R, T, Z> BiFunction<Optional<T>, Optional<R>, Optional<Z>> lift(BiFunction<? super T, ? super R, ? extends Z> function) {
        return (left, right) -> left.flatMap(leftVal -> right.map(rightVal -> function.apply(leftVal, rightVal)));
    }
}
