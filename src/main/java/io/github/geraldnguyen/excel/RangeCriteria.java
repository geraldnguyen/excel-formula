package io.github.geraldnguyen.excel;

import java.util.List;
import java.util.function.Predicate;

public class RangeCriteria<T> {
    public final List<T> range;
    public final Predicate<? super T> criteria;

    public RangeCriteria(List<T> range, Predicate<? super T> criteria) {
        this.range = range;
        this.criteria = criteria;
    }
}
