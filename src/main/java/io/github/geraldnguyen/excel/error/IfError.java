package io.github.geraldnguyen.excel.error;


import lombok.extern.log4j.Log4j2;

import java.util.function.Supplier;

@Log4j2
public class IfError {
    public <T> T ifError(Supplier<T> valueFn, Supplier<T> fallbackFn) {
        try {
            return valueFn.get();
        } catch (Exception e) {
            log.warn("Encounter error while executing value function", e);
            return fallbackFn.get();
        }
    }

    public <T, E extends Exception> T ifError(Supplier<T> valueFn, Supplier<T> fallbackFn, Class<E> exceptionType) {
        try {
            return valueFn.get();
        } catch (Throwable e) {
            log.warn("Encounter error while executing value function", e);
            if (exceptionType.isAssignableFrom(e.getClass())) {
                return fallbackFn.get();
            } else {
                log.error("Caught exception is not of expected type {}. Rethrow", exceptionType);
                throw e;
            }
        }
    }
}
