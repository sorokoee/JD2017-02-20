package by.it.sorokoee.calculator;

public class VarF  extends Var{
    public Double value;

    @Override
    public Var add(Var var)throws MathException {

        if (var instanceof VarF){
            VarF operand=(VarF)var;
           return new VarF(this.value+operand.value);
        } else return (var.add(this));

    }

    @Override
    public Var mul(Var var)throws MathException {

        if (var instanceof VarF){
            VarF operand=(VarF)var;
            return new VarF(this.value*operand.value);
        } else
            return var.mul(this);
    }

    @Override
    public Var div(Var var) throws MathException {

        if (var instanceof VarF){
            VarF operand=(VarF)var;
            if (operand.value==0)
                throw new MathException("деление на ноль не возможно");
            return new VarF(this.value/operand.value);
        } else return super.div(var);
    }

    @Override
    public Var sub(Var var) throws MathException {
        if (var instanceof VarF){
            VarF operand=(VarF)var;
            return new VarF(this.value-operand.value);
        } else return super.sub(var);

    }

    public VarF(String value) {
        fromString(value);
    }

    public VarF(double value) {
        this.value = value;
    }

    public VarF(VarF var) {
        this.value=var.value;
    }

    @Override
    public void fromString(String value) {
        this.value=Double.parseDouble(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
