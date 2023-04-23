package io.github.geraldnguyen.excel.math;

import io.github.geraldnguyen.excel.BaseIfs;
import io.github.geraldnguyen.excel.RangeCriteria;

import java.math.BigDecimal;
import java.util.List;

// https://support.microsoft.com/en-us/office/sumifs-function-c9e748f5-7ea7-455d-9406-611cebce642b
public class SumIfs extends BaseIfs {
    protected <T extends Number> T sumIfs(List<T> range, RangeCriteria<?>... rangeCriteria) {
        var elems = ifs(range, rangeCriteria);
        return elems.stream().reduce(SumIfs::sum).orElse(null);
    }

    public static <T extends Number> T sum(T n1, T n2) {
        if (n1 instanceof Integer) {
            return (T) Integer.valueOf(Integer.sum(n1.intValue(), n2.intValue()));
        }
        if (n1 instanceof Long) {
            return (T) Long.valueOf(Long.sum(n1.longValue(), n2.longValue()));
        }
        if (n1 instanceof Double) {
            return (T) Double.valueOf(Double.sum(n1.doubleValue(), n2.doubleValue()));
        }
        if (n1 instanceof Double) {
            return (T) Double.valueOf(Double.sum(n1.doubleValue(), n2.doubleValue()));
        }
        if (n1 instanceof BigDecimal) {
            return (T) ((BigDecimal) n1).add((BigDecimal) n2);
        }

        throw new IllegalArgumentException("Type not supported: " + n1.getClass());
    }

}
