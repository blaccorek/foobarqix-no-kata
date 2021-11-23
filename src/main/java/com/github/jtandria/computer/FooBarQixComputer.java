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

    private final Map<String, String> divisionRuleMap = new HashMap<String, String>() {
        {
            put("3", "Foo");
            put("5", "Bar");
            put("7", "Qix");
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
     * Convert input to given string if it match the given character.
     *
     * @param character     the character to match
     * @param convertString the returned string if match
     * @param input         the input used
     */
    public String computeMatchCharacterRule(int character, String convertString, char input) {
        return character == input ? convertString : "";
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
        String result;

        divisionRuleMap.forEach((key, translation) -> {
            try {
                final int number = Integer.parseInt(key);
                if (number != 0) {
                    resultBuilder.append(computeDivisibleRule(number, translation, toCompute));
                }
            } catch (WrongFormatException e) {
                LOGGER.severe("toCompute is not a number");
            }
        });
        result = toCompute.replace('0', ' ');
        for (char c : result.toCharArray()) {
            if (c == ' ') {
                resultBuilder.append(c);
            } else {
                divisionRuleMap.forEach((key, translation) -> {
                    resultBuilder.append(computeMatchCharacterRule(key.charAt(0), translation, c));
                });
            }
        }
        if (resultBuilder.toString().strip().length() > 0) {
            result = resultBuilder.toString();
        }
        return result.replace(' ', '*');
    }

}
