package pl.ejaksla.onlinejudge.domain.services;

import pl.ejaksla.onlinejudge.domain.model.EvaluationResult;
import pl.ejaksla.onlinejudge.domain.model.Problem;
import pl.ejaksla.onlinejudge.domain.model.Submission;

public interface ScoringService {

    String evaluateSolution(String submittedCode, String problemID, boolean isFinalSubmission);
    //TODO should problem be a part of submission?

}
