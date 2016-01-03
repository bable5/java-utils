java-utils
====

Wrapper to boot-strap running Java programs. The wrapper prompts the user to press ENTER before invoking the main method. Useful for things like hooking up a profiler to the JVM.

Usage
----

```bash
$> ./gradlew testClasses
$> java -cp build/classes/main:build/classes/test com.mooney_ware.classutils.TestHarness example.TestMain "Hello Launcher"
```
