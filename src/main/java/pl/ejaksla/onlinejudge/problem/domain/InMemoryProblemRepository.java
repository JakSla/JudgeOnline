package pl.ejaksla.onlinejudge.problem.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pl.ejaksla.onlinejudge.problem.dto.ProblemDto;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

/**
 * Created by Jakub Slawinski on 2019-11-13
 */
class InMemoryProblemRepository implements ProblemRepository {

    private ConcurrentHashMap<String, Problem> map = new ConcurrentHashMap<>();

    @Override
    public Problem save(Problem problem) {
        requireNonNull(problem);
        map.put(problem.dto().getName(), problem);
        return problem;
    }

    @Override
    public Problem findByName(String name) {
        return map.get(name);
    }

    @Override
    public Page<Problem> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }
}
