package org.lld.behavioral.interpreter.discount;

// Evaluates logical AND operations between two expressions
public class AndExpression implements Expression {
    private final Expression leftExpression;
    private final Expression rightExpression;

    public AndExpression(Expression left, Expression right) {
        this.leftExpression = left;
        this.rightExpression = right;
    }

    @Override
    public boolean interpret(Context context) {
        // Short-circuiting evaluation
        return leftExpression.interpret(context) && rightExpression.interpret(context);
    }
}