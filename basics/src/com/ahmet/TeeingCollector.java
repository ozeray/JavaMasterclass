package com.ahmet;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TeeingCollector {

    public static void main(String[] args) {
        double mean = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.teeing(Collectors.summingDouble(d -> d),
                        Collectors.counting(), ((sum, count) -> sum/count)));
        assert mean == 3.0;
    }
}
