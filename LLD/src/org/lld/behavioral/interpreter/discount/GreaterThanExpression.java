package org.lld.behavioral.interpreter.discount;

// Handles numerical comparisons like: parameterName > value
public class GreaterThanExpression implements Expression {
    private final String paramName;
    private final double threshold;

    public GreaterThanExpression(String paramName, double threshold) {
        this.paramName = paramName;
        this.threshold = threshold;
    }

    @Override
    public boolean interpret(Context context) {
        Object val = context.getParam(paramName);
        if (val instanceof Number) {
            return ((Number) val).doubleValue() > threshold;
        }
        return false;
    }
}
