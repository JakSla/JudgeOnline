package pl.ejaksla.onlinejudge.tempHappyPath;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import javax.tools.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class EvaluationService {
    SummaryGeneratingListener listener = new SummaryGeneratingListener();

    public static void main(String[] args) throws IOException {
        // 1. Get input from file or from web (whatever was created as solution for given task)
//        String code = readFile("C:\\Users\\ejaksla\\IdeaProjects\\JudgeOnline\\src\\main\\java\\pl\\ejaksla\\onlinejudge\\tempHappyPath\\HelloWorld", StandardCharsets.UTF_8);
        String code = readFile("C:\\Users\\ejaksla\\IdeaProjects\\JudgeOnline\\src\\main\\java\\pl\\ejaksla\\onlinejudge\\tempHappyPath\\Program.java", StandardCharsets.UTF_8);
//        String code = Files.lines(Paths.get("C:\\Users\\ejaksla\\IdeaProjects\\JudgeOnline\\src\\main\\java\\pl\\ejaksla\\onlinejudge\\tempHappyPath\\Program.java"), StandardCharsets.UTF_8);
        System.out.println("PRINTING OUT CODE");
        System.out.println(code);
        System.out.println("STOPPED PRINTING OUT CODE");

        // 2. For a given question either:
        /**
         * A) execute everything in given file (have a 1 file) where just Solution() method body is visible to edit
         * and test cases are hardcoded within this 1 file. Execution would be via main() method in this file.
         *
         * B) have separated ProgramTest file with test cases and execute them as well via gradle/maven? or something.
         *
         * in both cases input from web/file needs to be incorporated to the file sent for compilation/evaluation.
         */

        /** Example file:
         * import java.util.HashMap;
         *
         * class Program.java { //unchangable
         *   public static int[] twoNumberSum2(int[] array, int targetSum) { //unchangable
         *     HashMap<Integer, Boolean> nums = new HashMap<>();
         * 		for (int num : array) {
         * 			int potentialMatch = targetSum - num;
         *
         * 			if (nums.containsKey(potentialMatch)) {
         * 				return potentialMatch > num ? new int[] {num, potentialMatch} : new int[] {potentialMatch, num};
         *                        } else {
         * 				nums.put(num, true);
         *            }* 		}
         * 		return new int[0];
         *   }
         * }
         */

        // 3. Compile file programatically. //JavaCompiler API? And verify result. If correct - go on, if not, print error.
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        JavaFileObject file = new JavaSourceFromString("Program", code);
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);

        boolean result = task.call();
        for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
            System.out.println("Diagnostics, only in case of failure to compile");
            System.out.println(diagnostic.getCode());
            System.out.println(diagnostic.getKind());
            System.out.println(diagnostic.getPosition());
            System.out.println(diagnostic.getStartPosition());
            System.out.println(diagnostic.getEndPosition());
            System.out.println(diagnostic.getSource());
            System.out.println(diagnostic.getMessage(null)); // error message

        }
        System.out.println("Result: " + result);

        // 4. Evaluate against hidden test cases (for now implement "submit" support only), depends if in 2 was used A or B option
        RunJUnit5TestsFromJava runner = new RunJUnit5TestsFromJava();
        runner.runOne();

        TestExecutionSummary summary = runner.listener.getSummary();

        // 5. Return results and parse. (result/fails ratio, raw output, list of failed TCs)
        // 6. Print to user. (via cmdline or to web)
        summary.printTo(new PrintWriter(System.out));
    }
//    SummaryGeneratingListener listener = new SummaryGeneratingListener();


    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}

class JavaSourceFromString extends SimpleJavaFileObject {
    final String code;

    JavaSourceFromString(String name, String code) {
        super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}

class RunJUnit5TestsFromJava {
    SummaryGeneratingListener listener = new SummaryGeneratingListener();

    public void runOne() {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectClass(ProgramTest.class))
                .build();
        Launcher launcher = LauncherFactory.create();
        TestPlan testPlan = launcher.discover(request);
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
    }
}
