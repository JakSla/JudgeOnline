package pl.ejaksla.onlinejudge.problem.dto;

/**
 * Created by Jakub Slawinski on 2019-11-13
 */
public class ProblemNotFoundException extends RuntimeException {
    public ProblemNotFoundException(final String name) {
        super("No film of title " + name + " found", null, false, false);
    }
}
