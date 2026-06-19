package org.lld.behavioral.interpreter.discount;

// expression that encpsulates context and interpret it
public interface Expression {
    // Evaluates the current rule against the provided context state
    boolean interpret(Context context);
}