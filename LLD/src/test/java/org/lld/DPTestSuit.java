package org.lld;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

// this is a test suit for all design patterns because we have
// some design patterns which prints output on console so we need to setup console
// so that we can read from there
public class DPTestSuit {
    protected final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    protected final ByteArrayOutputStream errorStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out; // original one
    private final PrintStream originalErr = System.err; // original error stream

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setErr(new PrintStream(errorStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
