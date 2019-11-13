package pl.ejaksla.onlinejudge.problem.domain.services;

public interface ScoringService {

    //TODO should problemID and userID be a part of submission?
    String evaluateSolution(final String submittedCode,
                            final String input,
                            final String output);

}
