// A simple harness for delaying entry to Class.main(...) until ENTER is pressed.
//  Created by Sean Mooney.
//  Minor changes by Sarah Kabala.

import java.lang.reflect.*;

public class TestHarness {
    public static void main(String[] args) throws java.io.IOException {
        // TODO: Validate arguments.
        if(args.length < 1) {
            System.err.println("Please give the name of a Java class to run in the TestHarness.");
        } else {
            String className = args[0];
            String[] programArgs = null;
            if (args.length > 1) {
                programArgs = new String[args.length-1];
                System.arraycopy(args,1,programArgs,0,programArgs.length);
            }
            testHarness(className, programArgs);
        }
    }

    public static void testHarness(String className, String[] args) {
        // TODO: Handle exceptions more carefully.
        try{
            System.out.println("Press ENTER to begin "+className+".");
            System.in.read();
            Class<?> testClass = loadClass(className);
            Method mainMethod = testClass.getDeclaredMethod("main", String[].class);
            mainMethod.invoke(null, (Object) args);
        }catch(Exception ex){
            System.err.println("Exception encountered.");
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    static Class<?> loadClass(String className) throws ClassNotFoundException, SecurityException {
        Class c = Class.forName(className);
        return c;
    }
}

