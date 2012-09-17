
import java.lang.reflect.*;

public class TestHarness{
    public static void main(String[] args) throws java.io.IOException{
        //TODO: Checking for no args.
        if(args.length < 1){
            System.err.println("Must name the class to run");
        }

        String className = args[0];
        System.out.println("WARNING NOT COPYING OTHER ARGS FOR PROGRAM!");
        String[] programArgs = null;// Copy from 1 to end, assuming there are any more args.

        testHarness(className, programArgs);

    }

    public static void testHarness(String className, String[] args){

        //jajaja bad practice. TODO: Don't swallow exceptions.
        try{
            System.out.println("Press a key to begin");
            System.in.read();
            Class<?> testClass = loadClass(className);
            Method mainMethod = testClass.getDeclaredMethod("main", String[].class);
            mainMethod.invoke(null, (Object) args);
        }catch(Exception ex){
            System.err.println("Exception encountered");
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    static Class<?> loadClass(String className) throws ClassNotFoundException, SecurityException {
        Class c = Class.forName(className);
        return c;
    }
}

