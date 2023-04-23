package io.github.geraldnguyen.excel.math;

import io.github.geraldnguyen.excel.RangeCriteria;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.github.geraldnguyen.excel.Predicates.*;

class SumIfsTest {

    @Test
    void sumIfsInteger() {
        var quantities = List.of(5, 4, 15, 3, 22, 12, 10, 33);
        var products = List.of("Apples", "Apples", "Artichokes", "Artichokes",
                "Bananas", "Bananas", "Carrots", "Carrots");
        var salespersons = List.of("Tom", "Sarah", "Tom", "Sarah", "Tom", "Sarah", "Tom", "Sarah");

        assertEquals(BigDecimal.valueOf(20), (new SumIfs()).sumIfs(quantities,
                new RangeCriteria<>(products, startWith("A")),
                new RangeCriteria<>(salespersons, isEqual("Tom"))
        ));

        assertEquals(BigDecimal.valueOf(30), (new SumIfs()).sumIfs(quantities,
                new RangeCriteria<>(products, notEqual("Bananas")),
                new RangeCriteria<>(salespersons, isEqual("Tom"))
        ));

    }

    @Test
    void sumIfsLong() {
        var quantities = List.of(5L, 4L, 15L, 3L, 22L, 12L, 10L, 33L);
        var products = List.of("Apples", "Apples", "Artichokes", "Artichokes",
                "Bananas", "Bananas", "Carrots", "Carrots");
        var salespersons = List.of("Tom", "Sarah", "Tom", "Sarah", "Tom", "Sarah", "Tom", "Sarah");

        assertEquals(BigDecimal.valueOf(20L), (new SumIfs()).sumIfs(quantities,
                new RangeCriteria<>(products, startWith("A")),
                new RangeCriteria<>(salespersons, isEqual("Tom"))
        ));
    }

    @Test
    void sumIfsDouble() {
        var quantities = List.of(5.1, 4.2, 15.3, 3.4, 22.5, 12.6, 10.7, 33.8);
        var products = List.of("Apples", "Apples", "Artichokes", "Artichokes",
                "Bananas", "Bananas", "Carrots", "Carrots");
        var salespersons = List.of("Tom", "Sarah", "Tom", "Sarah", "Tom", "Sarah", "Tom", "Sarah");

        assertEquals(BigDecimal.valueOf(20.4), (new SumIfs()).sumIfs(quantities,
                new RangeCriteria<>(products, startWith("A")),
                new RangeCriteria<>(salespersons, isEqual("Tom"))
        ));
    }

    @Test
    void sumIfsBigDecimal() {
        var quantities = List.of(BigDecimal.valueOf(5.1), BigDecimal.valueOf(4.2), BigDecimal.valueOf(15.3),
                BigDecimal.valueOf(3.4), BigDecimal.valueOf(22.5), BigDecimal.valueOf(12.6),
                BigDecimal.valueOf(10.7), BigDecimal.valueOf(33.8));
        var products = List.of("Apples", "Apples", "Artichokes", "Artichokes",
                "Bananas", "Bananas", "Carrots", "Carrots");
        var salespersons = List.of("Tom", "Sarah", "Tom", "Sarah", "Tom", "Sarah", "Tom", "Sarah");

        assertEquals(BigDecimal.valueOf(20.4), (new SumIfs()).sumIfs(quantities,
                new RangeCriteria<>(products, startWith("A")),
                new RangeCriteria<>(salespersons, isEqual("Tom"))
        ));
    }
}