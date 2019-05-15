package pl.ejaksla.onlinejudge.tempHappyPath;


import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.tools.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

@RestController
@RequestMapping("/v1/api")
public class EvaluationController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "OK";
    }

    @PostMapping("/evaluate")
    public EvaluationResponseDTO evaluate(@RequestParam(name = "problemId") final String problemID,
                                   @RequestParam(name = "programmingLanguage") final String programmingLanguage,
                                   @RequestBody final String submittedCode) {

        // 1. Retrieved arguments
        System.out.println("ProblemID = " + problemID);
        System.out.println("Programming language is " + programmingLanguage);
        System.out.println("Submitted code:");
        System.out.println(submittedCode);

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
        JavaFileObject file = new JavaSourceFromString("Program", submittedCode);
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
        System.out.println("Compilation: " + result);

        RunJUnit5TestsFromJava runner = new RunJUnit5TestsFromJava();
        runner.runOne();
        TestExecutionSummary summary = runner.listener.getSummary();

        System.out.println("summary: " + summary.getTestsSucceededCount());
        return new EvaluationResponseDTO(EvaluationResponseDTO.Status.OK, "evaluation successful");

        // 4. Evaluate against hidden test cases (for now implement "submit" support only), depends if in 2 was used A or B option
//        RunJUnit5TestsFromJava runner = new RunJUnit5TestsFromJava();
//        runner.runOne();
//
//        TestExecutionSummary summary = runner.listener.getSummary();

        // 5. Return results and parse. (result/fails ratio, raw output, list of failed TCs)
        // 6. Print to user. (via cmdline or to web)
//        summary.printTo(new PrintWriter(System.out));
//        return "Hello, the time at the server is now " + new Date() + "\n";
    }

    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
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


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    String handleException(final IllegalArgumentException e) {
        return e.getMessage();
    }
}
//    @GetMapping("/evaluate")
//    public String evaluateForm(Model model) {
//        model.addAttribute("code", new CodeSubmition());
//        return "Hello, the time at the server is now " + new Date() + "\n";
//    }
//
//    @PostMapping("/greeting")
//    public String evaluateSubmit(@ModelAttribute String greeting) {
//        return "result";
//    }
