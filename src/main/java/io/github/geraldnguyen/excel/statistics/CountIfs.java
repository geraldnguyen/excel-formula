package io.github.geraldnguyen.excel.statistics;

import io.github.geraldnguyen.excel.BaseIfs;
import io.github.geraldnguyen.excel.RangeCriteria;

import java.util.List;

// https://support.microsoft.com/en-us/office/countifs-function-dda3dc6e-f74e-4aee-88bc-aa8c2a866842
public class CountIfs extends BaseIfs {
    public <T> long countIfs(RangeCriteria<?>... rangeCriteria) {
        var elems = ifs(rangeCriteria);
        return elems.size();
    }
}
