package com.mooney_ware.classutils;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestHarnessTest {

    @Test
    public void buildArgsFromSuppliedArgs() {
        String[] suppliedArgs = {"main.Main", "args1", "args2"};

        String[] programArgs = TestHarness.buildProgramArgs(suppliedArgs);

        assertArrayEquals(new String[]{"args1", "args2"}, programArgs);
        assertArrayEquals(new String[]{"main.Main", "args1", "args2"}, suppliedArgs);
    }

    @Test
    public void buildArgsFromMainClassOnlyIsEmptyArgs() {
        String[] suppliedArgs = {"main.Main"};

        String[] programArgs = TestHarness.buildProgramArgs(suppliedArgs);

        assertArrayEquals(new String[0], programArgs);
        assertArrayEquals(new String[]{"main.Main"}, suppliedArgs);

    }

    @Test
    public void findAndRunMainMethod() {
        ByteArrayOutputStream stubOut = new ByteArrayOutputStream();
        PrintStream out = stubSystemOut(stubOut);

        TestHarness.runMain("example.TestMain", new String[]{"Test Hello"});

        assertEquals("Test Hello\n", stubOut.toString());
    }

    private PrintStream stubSystemOut(ByteArrayOutputStream baos) {
        PrintStream sysout = System.out;
        System.setOut(new PrintStream(baos));

        return sysout;
    }

}
