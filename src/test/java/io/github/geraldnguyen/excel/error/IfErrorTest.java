package io.github.geraldnguyen.excel.error;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class IfErrorTest {

    @Test
    void returnFirstValue_ifNoError() {
        Supplier<Integer> firstValueFn = () -> 1;

        assertEquals(1, (new IfError()).ifError(firstValueFn, null) );
    }

    @Test
    void returnSecondValue_ifError() {
        Supplier<Integer> firstValueFn = () -> { throw new RuntimeException("test"); };
        Supplier<Integer> secondValueFn = () -> 2;

        assertEquals(2, (new IfError()).ifError(firstValueFn, secondValueFn) );
    }

    @Test
    void returnSecondValue_ifError_ofSpecificType() {
        Supplier<Integer> firstValueFn = () -> { throw new RuntimeException("test"); };
        Supplier<Integer> secondValueFn = () -> 2;

        // checked vs runtime exception
        assertThrows(RuntimeException.class, () -> (new IfError()).ifError(firstValueFn, secondValueFn, IOException.class) );

        // same runtime exception
        assertEquals(2, (new IfError()).ifError(firstValueFn, secondValueFn, RuntimeException.class) );

        // expected NPE but caught RuntimeException
        assertThrows(RuntimeException.class, () -> (new IfError()).ifError(firstValueFn, secondValueFn, NullPointerException.class) );
    }

    @Test
    void supportNumericReturnTypes() {
        // implicitly typed
        assertEquals(1.0, (new IfError()).ifError(() -> 1.0, () -> 2) );

        // explicitly typed
        Supplier<Number> firstValueFn = () -> 1.0;
        Supplier<Number> secondValueFn = () -> 2;
        assertEquals(1.0, (new IfError()).ifError(firstValueFn, secondValueFn) );

    }
}