package pl.ejaksla.onlinejudge.problem.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.ejaksla.onlinejudge.problem.dto.ProblemDto;

import javax.transaction.Transactional;

import static java.util.Objects.requireNonNull;

/**
 * Created by Jakub Slawinski on 2019-11-13
 */
@Transactional
public class ProblemFacade {

    private ProblemRepository problemRepository;
    private ProblemCreator problemCreator;

    public ProblemFacade(final ProblemRepository problemRepository, final ProblemCreator problemCreator) {
        this.problemRepository = problemRepository;
        this.problemCreator = problemCreator;
    }

    public ProblemDto addProblem(final ProblemDto problemDto) {
        requireNonNull(problemDto);
        Problem problem = problemCreator.from(problemDto);
        problemRepository.save(problem);
        return problem.dto();
    }

    public ProblemDto show(final String name) {
        requireNonNull(name);
        Problem problem = problemRepository.findOneOrThrow(name);
        return problem.dto();
    }

    public Page<ProblemDto> findAll(Pageable pageable) {
        requireNonNull(pageable);
        return problemRepository
                .findAll(pageable)
                .map(problem -> problem.dto());
    }
}
