package com.github.jtandria.computer;

import java.util.Random;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Test on FooBarQixComputer class.
 *
 * @author jtandria
 */
public class FooBarQixComputerTest {

    @ParameterizedTest
    @CsvSource({ "1, 1", "2, 2", "3, FooFoo", "4, 4", "5, BarBar", "6, Foo", "7, QixQix", "8, 8", "9, Foo", "10, Bar",
            "13, Foo", "15, FooBarBar", "21, FooQix", "33, FooFooFoo", "51, FooBar", "53, BarFoo" })
    void validationTest(ArgumentsAccessor arguments) {
        final String number = arguments.getString(0);
        final String expectedResult = arguments.getString(1);
        final IComputer computer = new FooBarQixComputer();
        final String result;

        result = computer.compute(number);
        Assertions.assertEquals(expectedResult, result,
                String.format("Compute function is not valid for input [%s]", number));
    }
}
