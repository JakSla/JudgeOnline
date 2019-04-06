package pl.ejaksla.onlinejudge.infrastructure;

import org.springframework.stereotype.Service;
import pl.ejaksla.onlinejudge.domain.ScoringService;
import pl.ejaksla.onlinejudge.domain.model.EvaluationResult;
import pl.ejaksla.onlinejudge.domain.model.Problem;
import pl.ejaksla.onlinejudge.domain.model.Submission;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class JavaScoringService implements ScoringService {

    @Override
    public EvaluationResult evaluateSolution(Submission submittedSolution, Problem problem) {
        String language = submittedSolution.getLanguage();
        String submittedCode = submittedSolution.getSubmittedSolution();
        long timeInMillis = 6000;
        String username = "TEST";

        System.out.println("Code started executing.");
        ProcessBuilder p;
        if (language.equals("java")) {
            p = new ProcessBuilder("java", "Main");
        } else if (language.equals("c")) {
            p = new ProcessBuilder("./Main");
        } else {
            p = new ProcessBuilder("./Main");
        }
        p.directory(new File(System.getProperty("dir")));
//        File in = new File(n);
//        p.redirectInput(in);
//        if (in.exists())
//            System.out.println("Input file " + in.getAbsolutePath());
//        p.redirectErrorStream(true);
//        System.out.println("Current directory " + System.getProperty("dir"));
//        File out = new File(System.getProperty("dir") + "/out(" + username + ").txt");
//
//        p.redirectOutput(out);
//        if (out.exists())
//            System.out.println("Output file generated " + out.getAbsolutePath());
        try {

            Process pp = p.start();
            if (!pp.waitFor(timeInMillis, TimeUnit.MILLISECONDS)) {
//                return verdict.TLE;
            }
            int exitCode = pp.exitValue();
            System.out.println("Exit Value = " + pp.exitValue());
//            if (exitCode != 0)
//                return verdict.RUN_ERROR;
        } catch (IOException ioe) {
            System.err.println("in execute() " + ioe);
        } catch (InterruptedException ex) {
            System.err.println(ex);
        }
        System.out.println("Code execution finished!");
        //delete executables
//        deleteExecutables(language);
//        return verdict.RUN_SUCCESS;
        return null;
    }


}
