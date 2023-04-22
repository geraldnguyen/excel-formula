package io.github.geraldnguyen.excel.math;

import io.github.geraldnguyen.excel.BaseIfs;
import io.github.geraldnguyen.excel.RangeCriteria;

import java.util.List;

// https://support.microsoft.com/en-us/office/sumifs-function-c9e748f5-7ea7-455d-9406-611cebce642b
public class SumIfs extends BaseIfs {
    protected Integer sumIfs(List<Integer> range, RangeCriteria<?>... rangeCriteria) {
        var elems = ifs(range, rangeCriteria);
        return elems.stream().reduce(Integer::sum).orElse(null);
    }
}
