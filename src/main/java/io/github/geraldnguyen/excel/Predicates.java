package io.github.geraldnguyen.excel;

import lombok.NonNull;

import java.util.function.Predicate;

public class Predicates {
    public static <T> Predicate<T> isEqual(@NonNull T constant) {
        return Predicate.isEqual(constant);
    }

    public static <T> Predicate<T> notEqual(@NonNull T constant) {
        return Predicate.not(Predicate.isEqual(constant));
    }

    public static <T extends Comparable<T>> Predicate<T> greaterThan(@NonNull T constant) {
        return t -> t.compareTo(constant) > 0;
    }

    public static Predicate<String> startWith(@NonNull String constant) {
        return s -> s.startsWith(constant);
    }
}
