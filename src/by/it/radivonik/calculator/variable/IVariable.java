package by.it.radivonik.calculator.variable;

import by.it.radivonik.calculator.exception.ParseException;

/**
 * Created by Radivonik on 13.03.2017.
 */
public interface IVariable {
    void fromString(String str) throws ParseException;
    String toString();
}
