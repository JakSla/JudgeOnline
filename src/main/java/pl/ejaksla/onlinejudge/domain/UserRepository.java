package pl.ejaksla.onlinejudge.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

    User findById(@Param("id") String id);
}
