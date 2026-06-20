package org.lld.behavioral.interpreter.discount;

import java.util.HashMap;
import java.util.Map;

// The context acts as a data repository containing the variable key-value pairs
// that the interpreter evaluates against.
public class Context {
    private final Map<String, Object> parameters = new HashMap<>();

    public void setParam(String key, Object value) {
        parameters.put(key, value);
    }

    public Object getParam(String key) {
        return parameters.get(key);
    }
}
