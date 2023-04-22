package io.github.geraldnguyen.excel.statistics;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BaseIfs {
    protected <T extends Comparable<T>> List<T> ifs(List<T> range, RangeCriteria<?>... rangeCriteria) {
        var size = range.size();
        for (var rc : rangeCriteria) {
            int providedSize = rc.range.size();
            if (providedSize != size) {
                throw new IllegalArgumentException("Invalid range, expected: " + size + ", provided: " + providedSize);
            }
        }

        var eligibles = Arrays.stream(rangeCriteria).map(rc -> {
            var list = (List<Object>) rc.range;
            var criteria = (Predicate<Object>) rc.criteria;
            return IntStream.range(0, size)
                    .filter(i -> criteria.test(list.get(i)))
                    .boxed()
                    .collect(Collectors.toSet());
        }).collect(Collectors.toList());

        var finalist = IntStream.range(0, size).boxed().collect(Collectors.toSet());
        eligibles.forEach(finalist::retainAll);

        return finalist.stream().map(range::get).collect(Collectors.toList());
    }
}
