package pl.ejaksla.onlinejudge.problem.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jakub Slawinski on 2019-11-13
 */
@Configuration
class ProblemConfiguration {

    ProblemFacade problemFacade() {
        return problemFacade(new InMemoryProblemRepository());
    }

    @Bean
    ProblemFacade problemFacade(final ProblemRepository problemRepository) {
        ProblemCreator problemCreator = new ProblemCreator();

        return new ProblemFacade(problemRepository, problemCreator);
    }
}
