package pl.ejaksla.onlinejudge.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProblemRepository extends CrudRepository<Problem, Long> {

    Problem findById(@Param("id") String id);

}
