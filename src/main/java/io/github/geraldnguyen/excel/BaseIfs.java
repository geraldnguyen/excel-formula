package io.github.geraldnguyen.excel;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BaseIfs {
    protected <T> List<T> ifs(List<T> range, RangeCriteria<?>... rangeCriteria) {
        var size = range.size();
        for (var rc : rangeCriteria) {
            int providedSize = rc.range.size();
            if (providedSize > size) {
                throw new IllegalArgumentException("Invalid range, max expected: " + size + ", provided: " + providedSize);
            }
        }

        var finalist = ifs(rangeCriteria);

        return finalist.stream().map(range::get).collect(Collectors.toList());
    }

    protected <T> Set<Integer> ifs(RangeCriteria<?>... rangeCriteria) {
        var eligibles = Arrays.stream(rangeCriteria).map(rc -> {
            var list = (List<Object>) rc.range;
            var criteria = (Predicate<Object>) rc.criteria;
            return IntStream.range(0, list.size())
                    .filter(i -> criteria.test(list.get(i)))
                    .boxed()
                    .collect(Collectors.toSet());
        }).collect(Collectors.toList());

        return eligibles.stream().reduce(null, (result, next) -> {
            if (result == null) {
                return next;
            }
            result.retainAll(next);
            return result;
        });
    }
}
