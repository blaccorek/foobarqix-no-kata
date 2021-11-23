package com.github.jtandria.computer;

import com.github.jtandria.computer.exception.WrongFormatException;

/**
 * @author jtandria
 */
public class FooBarQixComputer implements IComputer {

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
        // TODO Auto-generated method stub
        return null;
    }

}
