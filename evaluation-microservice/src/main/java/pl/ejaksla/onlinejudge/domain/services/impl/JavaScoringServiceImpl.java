package pl.ejaksla.onlinejudge.domain.services.impl;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.springframework.stereotype.Service;
import pl.ejaksla.onlinejudge.domain.services.ScoringService;

import javax.tools.*;
import java.net.URI;
import java.util.Arrays;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

@Service
public class JavaScoringServiceImpl implements ScoringService {

    @Override
    public String evaluateSolution(final String submittedCode, final String problemID, final boolean isFinalSubmission) {
        final boolean compilationResult = compile(submittedCode);
        final String testResults;

        if (compilationResult) {
                // run hidden test cases if this is final submission
                testResults = test(submittedCode, isFinalSubmission);
        } else {
            return "COMPILATION FAILED";
        }

        return "OK: " + testResults;
    }

    private boolean compile(final String submittedCode) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        JavaFileObject file = new JavaSourceFromString("Program", submittedCode);
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);

        boolean result = task.call();
        //TODO handle failed compilation
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
        return result;
    }

    /**
     * @param submittedCode
     * @param isFinalSubmission whether to run full hidden test cases suite
     * @return summary of test execution
     */
    private String test(String submittedCode, final boolean isFinalSubmission) {
        //TODO run full hidden tests if is final, sample test case otherwise
        //TODO handle errors
        RunJUnit5TestsFromJava runner = new RunJUnit5TestsFromJava();
        runner.runOne();
        TestExecutionSummary summary = runner.listener.getSummary();
        System.out.println("summary: " + summary.getTestsSucceededCount());
        return "" + summary.getTestsSucceededCount();
    }


    //HELPER CLASSES
    //Helper class for test execution
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

        void runOne() {
            LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                    .selectors(selectClass(ProgramTest.class))
                    .build();
            Launcher launcher = LauncherFactory.create();
            TestPlan testPlan = launcher.discover(request);
            launcher.registerTestExecutionListeners(listener);
            launcher.execute(request);
        }
    }
}