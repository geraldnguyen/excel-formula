package io.github.geraldnguyen.excel;

import lombok.NonNull;

import java.util.function.Predicate;

public class Predicates {
    public static <T> Predicate<T> isEqual(@NonNull T constant) {
        return Predicate.isEqual(constant);
    }

    public static <T extends Comparable<T>> Predicate<T> greaterThan(@NonNull T constant) {
        return t -> t.compareTo(constant) > 0;
    }
}
