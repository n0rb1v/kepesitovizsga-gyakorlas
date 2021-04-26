package hu.nive.ujratervezes.kepesitovizsga.numberofdigits;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumberOfDigits {
    public int getNumberOfDigits(int i) {
        return Stream.iterate(1, x -> x + 1)
                .limit(i)
                .map(x -> {
                    if (x > 9) {
                        x = 2;
                    }else {
                        x = 1;
                    }
                    return x;
                })
                .mapToInt(Integer::intValue)
                .sum();
    }
}
