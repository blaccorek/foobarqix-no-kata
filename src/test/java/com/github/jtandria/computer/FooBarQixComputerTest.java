package com.github.jtandria.computer;

import com.github.jtandria.computer.exception.WrongFormatException;
import java.util.Random;
import java.util.logging.Logger;
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
    static final Logger LOGGER = Logger.getLogger(FooBarQixComputerTest.class.getName());

    @ParameterizedTest
    @CsvSource({ "3, Foo", "5, Bar", "7, Qix" })
    void testDivisionRuleSuccess(ArgumentsAccessor arguments) {
        final int divisionRule = arguments.getInteger(0);
        final String convertString = arguments.getString(1);

        final int rangeStart = 1;
        final int rangeEnd = 1000;

        final FooBarQixComputer computer = new FooBarQixComputer();
        final Random rnd = new Random();

        IntStream.range(rangeStart, rangeEnd).forEach(n -> {
            final int randomNumber = rnd.nextInt();
            LOGGER.info(String.format("Random number is %d", randomNumber));
            final long randomDisibleNumber = (long) divisionRule * randomNumber;
            final String input = Long.toString(randomDisibleNumber);
            try {
                final String result = computer.computeDivisibleRule(divisionRule, convertString, input);
                Assertions.assertEquals(convertString, result,
                        String.format("Foo rule does not work for number %d", randomDisibleNumber));
            } catch (WrongFormatException e) {
                Assertions.fail("Exception received");
            }
        });
    }

    @Test
    void testDivisionRuleFailure() {

        final FooBarQixComputer computer = new FooBarQixComputer();
        final Random rnd = new Random();

        WrongFormatException thrown = Assertions.assertThrows(WrongFormatException.class, () -> {
            computer.computeDivisibleRule(rnd.nextInt(), "Not-returned", "not a number");
        }, "WrongFormatException was expected");

        Assertions.assertEquals("Input has wrong format", thrown.getMessage());
    }

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
