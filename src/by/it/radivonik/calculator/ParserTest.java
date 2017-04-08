package by.it.radivonik.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Radivonik on 09.04.2017.
 */
public class ParserTest {
    @Test
    public void parseCalcA1() throws Exception {
        String res = (new Parser().parseCalc("A=2+5.3"));
        assertEquals(res, "7.3");
    }

    @Test
    public void parseCalcA2() throws Exception {
        String res = (new Parser().parseCalc("B=A*3.5"));
        assertEquals(res, "25.55");
    }

    @Test
    public void parseCalcA3() throws Exception {
        String res = (new Parser().parseCalc("B1=B+0.11*-5"));
        assertEquals(res, "25.0");
    }

    @Test
    public void parseCalcA4() throws Exception {
        String res = (new Parser().parseCalc("B2=A/2-1"));
        assertEquals(res, "2.65");
    }

    @Test
    public void parseCalcB1() throws Exception {
        String res = (new Parser().parseCalc("C=B+(A*2)"));
        assertEquals(res, "40.15");
    }

    @Test
    public void parseCalcB2() throws Exception {
        String res = (new Parser().parseCalc("D=((C-0.15)-20)/(7-5)"));
        assertEquals(res, "10");
    }

    @Test
    public void parseCalcB3() throws Exception {
        String res = (new Parser().parseCalc("E={2,3}*(D/2)"));
        assertEquals(res, "{10.0,15.0}");
    }
}