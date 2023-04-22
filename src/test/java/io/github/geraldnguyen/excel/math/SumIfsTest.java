package io.github.geraldnguyen.excel.math;

import io.github.geraldnguyen.excel.RangeCriteria;
import org.junit.jupiter.api.Test;

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

        assertEquals(20, (new SumIfs()).sumIfs(quantities,
                new RangeCriteria<>(products, startWith("A")),
                new RangeCriteria<>(salespersons, isEqual("Tom"))
        ));

        assertEquals(30, (new SumIfs()).sumIfs(quantities,
                new RangeCriteria<>(products, notEqual("Bananas")),
                new RangeCriteria<>(salespersons, isEqual("Tom"))
        ));

    }
}