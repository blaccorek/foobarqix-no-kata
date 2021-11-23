package com.github.jtandria.computer;

import com.github.jtandria.computer.exception.WrongFormatException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author jtandria
 */
public class FooBarQixComputer implements IComputer {

    private static final Logger LOGGER = Logger.getLogger(FooBarQixComputer.class.getName());

    private static final int FOO_DIVISOR = 3;
    private static final int BAR_DIVISOR = 5;
    private static final int QIX_DIVISOR = 7;

    private final Map<Integer, String> divisionRulesMap = new HashMap<Integer, String>() {
        {
            put(FOO_DIVISOR, "Foo");
            put(BAR_DIVISOR, "Bar");
            put(QIX_DIVISOR, "Qix");
        }
    };

    /**
     * Convert input to given string if divisible by a given number.
     *
     * @param number the divisor
     * @param result the returned string if divisible
     * @param input  the input used as the dividend
     * @return Given string else an empty string
     * @throws WrongFormatException
     */
    public String computeDivisibleRule(int number, String result, String input) throws WrongFormatException {
        try {
            long dividend = Long.parseLong(input);
            if (dividend < 0) {
                dividend = -dividend;
            }
            return (dividend % number) == 0 ? result : "";
        } catch (NumberFormatException e) {
            throw new WrongFormatException("Input has wrong format", e);
        }
    }

    /**
     * Parse the string inputed as argument and convert it according to the
     * following rules :
     * <p>
     * <ul>
     * <li>If the number is divisible by 3, write “Foo” instead of the number
     * <li>If the number is divisible by 5, add “Bar”
     * <li>If the number is divisible by 7, add “Qix”
     * <li>For each digit 3, 5, 7, add “Foo”, “Bar”, “Qix” in the digits order.
     * </ul>
     * <p>
     *
     * @param toCompute string to parse
     * @return Converted input
     */
    @Override
    public String compute(String toCompute) {
        StringBuilder resultBuilder = new StringBuilder();

        divisionRulesMap.forEach((number, result) -> {
            try {
                resultBuilder.append(computeDivisibleRule(number, result, toCompute));
            } catch (WrongFormatException e) {
                LOGGER.severe("toCompute is not a number");
            }
        });
        if (resultBuilder.length() == 0) {
            return toCompute;
        }
        return resultBuilder.toString();
    }

}
