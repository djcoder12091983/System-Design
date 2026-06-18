package org.lld.behavioral.interpreter.discount;

// Handles strict equality checks like: parameterName == expectedValue
public class EqualsExpression implements Expression {
    private final String paramName;
    private final Object expectedValue;

    public EqualsExpression(String paramName, Object expectedValue) {
        this.paramName = paramName;
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean interpret(Context context) {
        Object actualValue = context.getParam(paramName);
        return expectedValue.equals(actualValue);
    }
}