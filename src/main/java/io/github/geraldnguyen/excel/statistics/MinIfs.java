package io.github.geraldnguyen.excel.statistics;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// https://support.microsoft.com/en-us/office/minifs-function-6ca1ddaa-079b-4e74-80cc-72eef32e6599
public class MinIfs extends BaseIfs {
    public <T extends Comparable<T>> T minIfs(List<T> range, RangeCriteria<?>... rangeCriteria) {
        var elems = ifs(range, rangeCriteria);
        return elems.stream().min(Comparable::compareTo).orElse(null);
    }
}
