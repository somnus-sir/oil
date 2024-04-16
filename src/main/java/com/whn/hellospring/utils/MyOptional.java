package com.whn.hellospring.utils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

public class MyOptional {

    public static <T> List<T> toList(Optional<T> option) {
        return option.
                map(Collections::singletonList).
                orElse(Collections.emptyList());
    }

    public static <R, A, T> R collect(Optional<T> option, Collector<? super T, A, R> collector) {
        final A container = collector.supplier().get();
        option.ifPresent(v -> collector.accumulator().accept(container, v));
        return collector.finisher().apply(container);
    }
}
