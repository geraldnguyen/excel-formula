package io.github.geraldnguyen.excel.math;

import io.github.geraldnguyen.excel.BaseIfs;
import io.github.geraldnguyen.excel.RangeCriteria;

import java.math.BigDecimal;
import java.util.List;

// https://support.microsoft.com/en-us/office/sumifs-function-c9e748f5-7ea7-455d-9406-611cebce642b
public class SumIfs extends BaseIfs {
    public  <T extends Number> BigDecimal sumIfs(List<T> range, RangeCriteria<?>... rangeCriteria) {
        var elems = ifs(range, rangeCriteria);
        return elems.stream()
                .reduce(BigDecimal.ZERO, SumIfs::sum, BigDecimal::add);
    }

    public static <T extends Number> BigDecimal sum(BigDecimal result, T n) {
        if (n instanceof Integer) {
            return result.add(new BigDecimal((Integer) n));
        }
        if (n instanceof Long) {
            return result.add(new BigDecimal((Long) n));
        }
        if (n instanceof Double) {
            return result.add(BigDecimal.valueOf((Double) n));
        }
        if (n instanceof BigDecimal) {
            return result.add((BigDecimal) n);
        }

        throw new IllegalArgumentException("Type not supported: " + n.getClass());
    }
}
