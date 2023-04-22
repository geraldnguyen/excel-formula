package io.github.geraldnguyen.excel.statistics;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// https://support.microsoft.com/en-us/office/maxifs-function-dfd611e6-da2c-488a-919b-9b6376b28883
public class MaxIfs extends BaseIfs {
    public <T extends Comparable<T>> T maxIfs(List<T> range, RangeCriteria<?>... rangeCriteria) {
        var elems = ifs(range, rangeCriteria);
        return elems.stream().max(Comparable::compareTo).orElse(null);
    }
}
