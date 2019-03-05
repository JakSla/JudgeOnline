package pl.ejaksla.onlinejudge.domain;

import pl.ejaksla.onlinejudge.domain.model.EvaluationResult;
import pl.ejaksla.onlinejudge.domain.model.Problem;
import pl.ejaksla.onlinejudge.domain.model.Submission;

public interface ScoringService {

    EvaluationResult evaluateSolution(Submission submittedSolution, Problem problem);
    //TODO should problem be a part of submission?

}
