package com.mooney_ware.classutils;

import java.lang.reflect.Method;

/**
 * A simple harness for delaying entry to Class.main(...) until ENTER is pressed.
 *
 * @author Sean Mooney, Sarah Kabala.
 */
public class TestHarness {
    public static void main(String[] args) throws java.io.IOException {
        if (args.length < 1) {
            System.err.println("Please give the name of a Java class to run in the TestHarness.");
        } else {
            String className = args[0];
            String[] programArgs = buildProgramArgs(args);
            testHarness(className, programArgs);
        }
    }

    public static void testHarness(String className, String[] args) {
        try {
            System.out.println("Press ENTER to begin " + className + ".");
            System.in.read();
            runMain(className, args);
        } catch (Exception ex) {
            System.err.println("Exception encountered.");
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    static Class<?> loadClass(String className) throws ClassNotFoundException, SecurityException {
        return Class.forName(className);
    }

    static String[] buildProgramArgs(String[] args) {
        String[] programArgs;

        if (args.length > 1) {
            programArgs = new String[args.length - 1];
            System.arraycopy(args, 1, programArgs, 0, programArgs.length);
        } else {
            programArgs = new String[0];
        }

        return programArgs;
    }

    public static void runMain(String className, String[] args) {
        try {
            Class<?> testClass = loadClass(className);
            Method mainMethod = testClass.getDeclaredMethod("main", String[].class);
            mainMethod.invoke(null, (Object) args);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
