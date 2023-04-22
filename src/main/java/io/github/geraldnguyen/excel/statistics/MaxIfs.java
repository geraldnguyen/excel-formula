package io.github.geraldnguyen.excel.statistics;

import io.github.geraldnguyen.excel.BaseIfs;
import io.github.geraldnguyen.excel.RangeCriteria;

import java.util.List;

// https://support.microsoft.com/en-us/office/maxifs-function-dfd611e6-da2c-488a-919b-9b6376b28883
public class MaxIfs extends BaseIfs {
    public <T extends Comparable<T>> T maxIfs(List<T> range, RangeCriteria<?>... rangeCriteria) {
        var elems = ifs(range, rangeCriteria);
        return elems.stream().max(Comparable::compareTo).orElse(null);
    }
}
