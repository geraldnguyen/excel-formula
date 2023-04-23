package io.github.geraldnguyen.excel.statistics;

import io.github.geraldnguyen.excel.BaseIfs;
import io.github.geraldnguyen.excel.RangeCriteria;
import io.github.geraldnguyen.excel.math.SumIfs;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

// https://support.microsoft.com/en-us/office/averageifs-function-48910c45-1fc0-4389-a028-f7c5c3001690
public class AverageIfs extends BaseIfs {
    public  <T extends Number> BigDecimal averageIfs(List<T> range, RangeCriteria<?>... rangeCriteria) {
        var total = (new SumIfs()).sumIfs(range, rangeCriteria);
        var elems = ifs(range, rangeCriteria);

        return total.divide(BigDecimal.valueOf(elems.size()), MathContext.DECIMAL128);
    }
}
