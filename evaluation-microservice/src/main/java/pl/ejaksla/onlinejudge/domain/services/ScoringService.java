package pl.ejaksla.onlinejudge.domain.services;

import pl.ejaksla.onlinejudge.domain.model.EvaluationResult;
import pl.ejaksla.onlinejudge.domain.model.Problem;
import pl.ejaksla.onlinejudge.domain.model.Submission;

public interface ScoringService {

    //TODO should problemID and userID be a part of submission?
    String evaluateSolution(final String submittedCode,
                            final String hiddenTestCases,
                            final String publicTestCases,
                            final String problemID,
                            final boolean isFinalSubmission);

}
