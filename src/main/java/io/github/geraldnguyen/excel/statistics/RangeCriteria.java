package io.github.geraldnguyen.excel.statistics;

import java.util.List;
import java.util.function.Predicate;

public class RangeCriteria<T> {
    public final List<T> range;
    public final Predicate<T> criteria;

    public RangeCriteria(List<T> range, Predicate<T> criteria) {
        this.range = range;
        this.criteria = criteria;
    }
}
