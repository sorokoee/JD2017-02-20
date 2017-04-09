package by.it.radivonik.calculator;

import java.util.*;

/**
 * Created by Radivonik on 08.04.2017.
 */
public class Operations {
    private static Map<String,Operation> operations = new HashMap<>();

    static {
        operations.put("(",new Operation("(", IOperation.Type.BracketLeft, 0));
        operations.put(")",new Operation(")", IOperation.Type.BracketRight, 0));
        operations.put("+",new Operation("+", IOperation.Type.TwoArg, 2));
        operations.put("-",new Operation("-", IOperation.Type.TwoArg, 2));
        operations.put("*",new Operation("*", IOperation.Type.TwoArg, 4));
        operations.put("/",new Operation("/", IOperation.Type.TwoArg, 4));
        operations.put("=",new Operation("=", IOperation.Type.TwoArg, 1));
        operations.put("printvar",new Operation("printvar", IOperation.Type.NoArg, 0));
        operations.put("sortvar",new Operation("sortvar", IOperation.Type.NoArg, 0));
    }

    static String calc(IOperation op, String... args) throws MathException, ParseException {
        String res = null;
        switch(op.getType()) {
            case NoArg:
                res = calcNoArg(op); break;
            case TwoArg:
                if (op.getOperator() == "=")
                    res = Var.setVar(args[0],args[1]).toString();
                else
                    res = calcTwoArg(op, Var.createVar(args[0]), Var.createVar(args[1]));
                break;
        }
        return res;
    }

    static Operation getOperation(String operator) {
       return operations.get(operator);
    }

    static String getPattern() {
        StringBuilder patternBracket = new StringBuilder("");
        StringBuilder patternNoArg = new StringBuilder("");
        StringBuilder patternTwoArg = new StringBuilder("");
        for (Operation op: operations.values()) {
            if ( op.getType() == IOperation.Type.BracketLeft || op.getType() == IOperation.Type.BracketRight) {
                patternBracket.append(op.getOperator());
            }
            if ( op.getType() == IOperation.Type.NoArg) {
                patternNoArg.append("|").append(op.getOperator());
            }
            else if ( op.getType() == IOperation.Type.TwoArg) {
                if (op.getOperator() == "-")
                    patternTwoArg.append("\\");
                patternTwoArg.append(op.getOperator());
            }
        }
        return "(?<=[^{," + patternTwoArg + "])([" + patternTwoArg + "])|[" + patternBracket + "]" + patternNoArg;
    }

    private static String calcNoArg(IOperation op) {
        String res = null;
        switch (op.getOperator()) {
            case "printvar":
                res = Var.getPrintVar(); break;
            case "sortvar":
                res = Var.getSortVar(); break;
        }
        return res;
    }

    private static String getOp(IOperation op, Var v1, Var v2) {
        return v1.toString() + op.getOperator() + v2.toString();
    }

    private static String calcTwoArg(IOperation op, Var v1, Var v2) throws MathException {
        if (v1 instanceof VarFloat && v2 instanceof VarFloat) {
            return calcTwoArg(op, (VarFloat)v1, (VarFloat)v2);
        }
        else if (v1 instanceof VarFloat && v2 instanceof VarVector) {
            return calcTwoArg(op, (VarFloat)v1,(VarVector)v2);
        }
        else if (v1 instanceof VarVector && v2 instanceof VarFloat) {
            return calcTwoArg(op, (VarVector)v1,(VarFloat)v2);
        }
        else if (v1 instanceof VarVector && v2 instanceof VarVector) {
            return calcTwoArg(op, (VarVector)v1,(VarVector)v2);
        }
        else
            return null;
    }

    private static String calcTwoArg(IOperation op, VarFloat v1, VarFloat v2) throws MathException {
        Var res = null;
        switch (op.getOperator()) {
            case "+":
                res = new VarFloat(v1.getValue() + v2.getValue()); break;
            case "-":
                res = new VarFloat(v1.getValue() - v2.getValue()); break;
            case "*":
                res = new VarFloat(v1.getValue() * v2.getValue()); break;
            case "/":
                if (v2.getValue() == 0)
                    throw new MathException("Деление на ноль: " + getOp(op,v1,v2));
                res = new VarFloat(v1.getValue() / v2.getValue()); break;
            default:
                return null;
        }
        return res.toString();
    }

    private static String calcTwoArg(IOperation op, VarFloat v1, VarVector v2) throws MathException {
        if (op.getOperator() == "+" || op.getOperator() == "*")
            return calcTwoArg(op,v2,v1);
        else {
            throw new MathException("Недопустимая операция: " + getOp(op,v1,v2));
        }
    }

    private static String calcTwoArg(IOperation op, VarVector v1, VarFloat v2) throws MathException {
        double[] v = new double[v1.length()];
        for (int i = 0; i < v1.length(); i++)
            v[i] = v2.getValue();
        return calcTwoArg(op, v1, new VarVector(v));
    }

    private static String calcTwoArg(IOperation op, VarVector v1, VarVector v2) throws MathException {
        if (v1.length() != v2.length()) {
            throw new MathException("У векторов в операции разная длина: " + getOp(op,v1,v2));
        }
        double[] res = new double[v1.length()];
        for (int i = 0; i < v1.length(); i++) {
            switch(op.getOperator()) {
                case "+":
                    res[i] = v1.getItem(i) + v2.getItem(i); break;
                case "-":
                    res[i] = v1.getItem(i) - v2.getItem(i); break;
                case "*":
                    res[i] = v1.getItem(i) * v2.getItem(i); break; // некорректная математическая операция
                case "/":
                    if (v2.getItem(i) == 0)
                        throw new MathException("Деление на ноль: " + getOp(op,v1,v2));
                    res[i] = v1.getItem(i) / v2.getItem(i); break;
                default:
                    return null;
            }
        }
        return new VarVector(res).toString();
    }
}
