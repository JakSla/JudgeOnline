package pl.ejaksla.onlinejudge.problem.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import pl.ejaksla.onlinejudge.problem.dto.ProblemDto;
import pl.ejaksla.onlinejudge.problem.dto.ProblemNotFoundException;

interface ProblemRepository extends Repository<Problem, Long> {

    Problem save(final Problem problem);
    Problem findByName(final String name);
    Page<Problem> findAll(Pageable pageable);

    default Problem findOneOrThrow(final String name) {
        Problem problem = findByName(name);
        if (problem == null) {
            throw new ProblemNotFoundException(name);
        }
        return problem;
    }
}
