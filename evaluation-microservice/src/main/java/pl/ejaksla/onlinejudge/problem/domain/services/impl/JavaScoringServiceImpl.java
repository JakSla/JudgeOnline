package pl.ejaksla.onlinejudge.problem.domain.services.impl;

import org.springframework.stereotype.Service;
import pl.ejaksla.onlinejudge.problem.domain.services.ScoringService;

import javax.tools.*;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class JavaScoringServiceImpl implements ScoringService {

    @Override
    public String evaluateSolution(final String submittedCode,
                                   final String input,
                                   final String output) {
        String testResults = null;
        try {
            testResults = testSubmission(submittedCode, input, output);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return testResults;
    }

    //TODO separate compile from test?
    private boolean compile(final String submittedCode) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        JavaFileObject file = new JavaSourceFromString("ProgramNew2", submittedCode);
        Iterable<? extends JavaFileObject> compilationUnits = Collections.singletonList(file);
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
     * @param submittedCode submitted code
     * @param input         input to testSubmission sumbitted code
     * @param output        expected output
     * @return summary of testSubmission execution
     */
    public String testSubmission(final String submittedCode,
                                 final String input,
                                 final String output) throws IOException {

        try (PrintWriter out = new PrintWriter("C:\\Users\\User\\Desktop\\ProgramNew.java")) {
            out.println(submittedCode);
        }

        List<String> inputParams = new ArrayList<>();
        inputParams.add("C:\\Program Files\\Java\\jdk-11.0.1\\bin\\java.exe");
        inputParams.add("C:\\Users\\User\\Desktop\\ProgramNew.java");

        //TODO make sure that input can be handled via System.in, not via args
        List<String> splittledInput = Arrays.asList(input.split(" "));
        inputParams.addAll(splittledInput);

        ProcessBuilder builder = new ProcessBuilder(inputParams);
        builder.redirectErrorStream(true);
        Process p = builder.start();

        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
        }
        final String answer = sb.toString();

        if (answer.equals(output)) {
            return "CORRECT ANSWER: " + answer;
        } else {
            return "INCORRECT ANSWER: " + answer;
        }
    }


    //HELPER CLASSES
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

}
