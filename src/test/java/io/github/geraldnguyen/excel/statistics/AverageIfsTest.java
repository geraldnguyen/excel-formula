package io.github.geraldnguyen.excel.statistics;

import io.github.geraldnguyen.excel.RangeCriteria;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static io.github.geraldnguyen.excel.Predicates.*;

class AverageIfsTest {

    @Test
    void example1() {
        var students = Arrays.asList(null, null, "Emilio", "Julie", "Hans", "Frederique");
        var firsts = List.of("Quiz", "Grade", 75, 94, 86, "Incomplete");
        var seconds = List.of("Quiz", "Grade", 85, 80, 93, 75);
        var finals = List.of("Exam", "Grade", 87, 88, "Incomplete", 75);

        // =AVERAGEIFS(B2:B5, B2:B5, ">70", B2:B5, "<90")
        List<Integer> b2b5 = firsts.subList(1, 4).stream()
                .filter(e -> e instanceof Integer)
                .map(e -> (Integer) e)
                .collect(Collectors.toList());
        assertEquals(BigDecimal.valueOf(75), (new AverageIfs()).averageIfs(b2b5,
                new RangeCriteria<>(b2b5, greaterThan(70)),
                new RangeCriteria<>(b2b5, lessThan(90))
        ));

        // =AVERAGEIFS(C2:C5, C2:C5, ">95")
        List<Integer> c2c5 = seconds.subList(1, 4).stream()
                .filter(e -> e instanceof Integer)
                .map(e -> (Integer) e)
                .collect(Collectors.toList());
        assertThrows(ArithmeticException.class, () -> (new AverageIfs()).averageIfs(c2c5,
                new RangeCriteria<>(c2c5, greaterThan(95))
        ));

        // =AVERAGEIFS(D2:D5, D2:D5, "<>Incomplete", D2:D5, ">80")
        List<Integer> d2d5 = finals.subList(1, 4).stream()
                .filter(e -> e instanceof Integer)
                .map(e -> (Integer) e)
                .collect(Collectors.toList());
        assertEquals(BigDecimal.valueOf(87.5), (new AverageIfs()).averageIfs(d2d5,
                new RangeCriteria<>(d2d5, notEqual("Incomplete")),
                new RangeCriteria<>(d2d5, greaterThan(80))
        ));
    }

    @Test
    void example2() {
        var types = List.of("Cozy Rambler", "Snug Bungalow", "Cool Cape Codder",
                "Splendid Split Level", "Exclusive Tudor", "Classy Colonial");
        var prices = List.of(230000, 197000, 345678, 321900, 450000, 395000);
        var towns = List.of("Issaquah", "Bellevue", "Bellevue", "Issaquah", "Bellevue", "Bellevue");
        var bedrooms = List.of(3, 2, 4, 2, 5, 4);
        var garages = List.of(false, true, true, true, true, false);

        // =AVERAGEIFS(B2:B7, C2:C7, "Bellevue", D2:D7, ">2",E2:E7, "Yes")
        assertEquals(BigDecimal.valueOf(397839), (new AverageIfs()).averageIfs(prices,
                new RangeCriteria<>(towns, isEqual("Bellevue")),
                new RangeCriteria<>(bedrooms, greaterThan(2)),
                new RangeCriteria<>(garages, isTrue())
        ));

        // =AVERAGEIFS(B2:B7, C2:C7, "Issaquah", D2:D7, "<=3",E2:E7, "No")
        assertEquals(BigDecimal.valueOf(230000), (new AverageIfs()).averageIfs(prices,
                new RangeCriteria<>(towns, isEqual("Issaquah")),
                new RangeCriteria<>(bedrooms, lessThanOrEqual(3)),
                new RangeCriteria<>(garages, isFalse())
        ));
    }
}