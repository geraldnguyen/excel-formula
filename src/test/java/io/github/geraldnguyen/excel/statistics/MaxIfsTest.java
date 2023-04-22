package io.github.geraldnguyen.excel.statistics;

import io.github.geraldnguyen.excel.statistics.MaxIfs;
import io.github.geraldnguyen.excel.statistics.RangeCriteria;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.github.geraldnguyen.Predicates.*;

class MaxIfsTest {
    // against https://support.microsoft.com/en-us/office/maxifs-function-dfd611e6-da2c-488a-919b-9b6376b28883

    @Test
    void example1() {
        var grades = List.of(89, 93, 96, 85, 91, 88);
        var weights = List.of(1, 2, 2, 3, 1, 1);

        assertEquals(91, new MaxIfs().maxIfs(grades,
                new RangeCriteria<>(weights, isEqual(1))));
    }

    @Test
    void example2() {
        var weights = List.of(10, 1, 100, 1, 1, 1);
        var grades = List.of("b", "a", "a", "b", "a", "a");

        assertEquals(10, new MaxIfs().maxIfs(weights.subList(0, 4),
                new RangeCriteria<>(grades.subList(1, 5), isEqual("a"))));
    }

    @Test
    void example3() {
        var weights = List.of(10, 1, 100, 1, 1, 50);
        var grades = List.of("b", "a", "a", "b", "a", "b");
        var classes = List.of("Business", "Technical", "Business", "Technical", "Technical", "Business");
        var levels = List.of(100, 100, 200, 300, 100, 400);

        assertEquals(50, new MaxIfs().maxIfs(weights,
                new RangeCriteria<>(grades, isEqual("b")),
                new RangeCriteria<>(levels, greaterThan(100))
        ));
    }

    @Test
    void example4() {
        var weights = List.of(10, 1, 100, 11, 1, 12);
        var grades = List.of("b", "a", "a", "b", "a", "b");
        var classes = List.of("Business", "Technical", "Business", "Technical", "Technical", "Business");
        var levels = List.of(8, 8, 8, 0, 8, 0);

        assertEquals(12, new MaxIfs().maxIfs(weights,
                new RangeCriteria<>(grades, isEqual("b")),
                new RangeCriteria<>(levels, isEqual(0))
                // how to represent this???
                // TODO: The criteria2 argument is A8. However, because A8 is empty, it is treated as 0 (zero).
        ));
    }

    @Test
    void example5() {
        var weights = List.of(10, 1, 100, 1, 1, 1);
        var grades = List.of("b", "a", "a", "b", "a", "a");

        // =MAXIFS(A2:A5,B2:c6,"a")  #VALUE!
        // Because the size and shape of the max_range and criteria_range aren't the same, MAXIFS returns the #VALUE! error.

        // Not supporting range criteria spanning multiple columns
        // hence this is not a valid scenario
    }

    @Test
    void example6() {
        var weights = List.of(10, 1, 100, 1, 1, 1);
        var grades = List.of("b", "a", "a", "b", "a", "a");
        var classes = List.of("Business", "Technical", "Business", "Technical", "Technical", "Business");
        var levels = List.of(100, 100, 200, 300, 100, 400);

        // =MAXIFS(A2:A6,B2:B6,"a",D2:D6,">200") = 0: No cells match the criteria
        assertEquals(null, new MaxIfs().maxIfs(weights.subList(0, 5),
                new RangeCriteria<>(grades.subList(0, 5), isEqual("a")),
                new RangeCriteria<>(levels.subList(0, 5), greaterThan(200))
        ));
    }
}