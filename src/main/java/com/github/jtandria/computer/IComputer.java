package com.github.jtandria.computer;

/**
 * @author jtandria
 */
public interface IComputer {
    /**
     * Parse the string inputed as argument and convert it following given rules
     * depending on the implementation.
     *
     * @param toCompute string to parse
     * @return Converted input
     */
    String compute(String toCompute);
}
