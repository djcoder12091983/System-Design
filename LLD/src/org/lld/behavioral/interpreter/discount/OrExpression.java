package org.lld.behavioral.interpreter.discount;

// Evaluates logical OR operations between two expressions
public class OrExpression implements Expression {
    private final Expression leftExpression;
    private final Expression rightExpression;

    public OrExpression(Expression left, Expression right) {
        this.leftExpression = left;
        this.rightExpression = right;
    }

    @Override
    public boolean interpret(Context context) {
        // Short-circuiting evaluation
        return leftExpression.interpret(context) || rightExpression.interpret(context);
    }
}