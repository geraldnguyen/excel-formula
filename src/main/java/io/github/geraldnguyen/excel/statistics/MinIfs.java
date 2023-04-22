package io.github.geraldnguyen.excel.statistics;

import io.github.geraldnguyen.excel.BaseIfs;
import io.github.geraldnguyen.excel.RangeCriteria;

import java.util.List;

// https://support.microsoft.com/en-us/office/minifs-function-6ca1ddaa-079b-4e74-80cc-72eef32e6599
public class MinIfs extends BaseIfs {
    public <T extends Comparable<T>> T minIfs(List<T> range, RangeCriteria<?>... rangeCriteria) {
        var elems = ifs(range, rangeCriteria);
        return elems.stream().min(Comparable::compareTo).orElse(null);
    }
}
