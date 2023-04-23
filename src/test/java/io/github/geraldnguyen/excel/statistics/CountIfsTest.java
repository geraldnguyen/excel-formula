package io.github.geraldnguyen.excel.statistics;

import io.github.geraldnguyen.excel.RangeCriteria;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static io.github.geraldnguyen.excel.Predicates.*;

class CountIfsTest {

    @Test
    void example1() {
        var salespersons = List.of("Davidoski", "Burke", "Sundaram", "Levitan");
        var exceedQ1Quota = List.of(true, true, true, false);
        var exceedQ2Quota = List.of(false, true, true, true);
        var exceedQ3Quota = List.of(false, false, true, true);

        // =COUNTIFS(B2:D2,"=Yes")
        var davidoskiResults = List.of(exceedQ1Quota.get(0), exceedQ2Quota.get(0), exceedQ3Quota.get(0));
        assertEquals(1, (new CountIfs()).countIfs(
                new RangeCriteria<>(davidoskiResults, isTrue())
        ));

        // =COUNTIFS(B2:B5,"=Yes",C2:C5,"=Yes")
        assertEquals(2, (new CountIfs()).countIfs(
                new RangeCriteria<>(exceedQ1Quota, isTrue()),
                new RangeCriteria<>(exceedQ2Quota, isTrue())
        ));

        // =COUNTIFS(B5:D5,"=Yes",B3:D3,"=Yes")
        var burkeResults = List.of(exceedQ1Quota.get(1), exceedQ2Quota.get(1), exceedQ3Quota.get(1));
        var levitanResults = List.of(exceedQ1Quota.get(3), exceedQ2Quota.get(3), exceedQ3Quota.get(3));
        assertEquals(1, (new CountIfs()).countIfs(
                new RangeCriteria<>(burkeResults, isTrue()),
                new RangeCriteria<>(levitanResults, isTrue())
        ));
    }

    @Test
    void example2() {
        var numbers = List.of(1, 2, 3, 4, 5, 6);
        var dates = List.of(LocalDate.of(2011, 5, 1), LocalDate.of(2011, 5, 2),
                LocalDate.of(2011, 5, 3), LocalDate.of(2011, 5, 4),
                LocalDate.of(2011, 5, 5), LocalDate.of(2011, 5, 6));

        // =COUNTIFS(A2:A7,"<6",A2:A7,">1")
        assertEquals(4, (new CountIfs()).countIfs(
                new RangeCriteria<>(numbers, lessThan(6)),
                new RangeCriteria<>(numbers, greaterThan(1))
        ));

        // =COUNTIFS(A2:A7, "<5",B2:B7,"<5/3/2011")
        assertEquals(2, (new CountIfs()).countIfs(
                new RangeCriteria<>(numbers, lessThan(5)),
                new RangeCriteria<>(dates, lessThan(LocalDate.of(2011, 5, 3)))
        ));

        // =COUNTIFS(A2:A7, "<" & A6,B2:B7,"<" & B4)
        // not supporting

    }
}