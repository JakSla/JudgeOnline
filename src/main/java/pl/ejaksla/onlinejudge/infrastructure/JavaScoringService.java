package pl.ejaksla.onlinejudge.infrastructure;

import org.springframework.stereotype.Service;
import pl.ejaksla.onlinejudge.domain.model.EvaluationResult;
import pl.ejaksla.onlinejudge.domain.model.Problem;
import pl.ejaksla.onlinejudge.domain.ScoringService;
import pl.ejaksla.onlinejudge.domain.model.Submission;

@Service
public class JavaScoringService implements ScoringService {

    @Override
    public EvaluationResult evaluateSolution(Submission submittedSolution, Problem problem) {


        return null;
    }


}
