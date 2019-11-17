package pl.ejaksla.onlinejudge.problem.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import pl.ejaksla.onlinejudge.problem.dto.ProblemDto
import pl.ejaksla.onlinejudge.problem.dto.ProblemNotFoundException
import spock.lang.Specification

class ProblemFacadeTest extends Specification implements SampleProblems {

    ProblemFacade facade = new ProblemConfiguration().problemFacade()

    def "should add problem"() {
        given: "problem is in the system"
        facade.addProblem(easyProblem)

        expect: "system should return the problem"
        facade.show(easyProblem.name) == easyProblem
    }

    def "should throw exception when asked for a problem that's not in the system"() {
        when: "system is asked for a problem that is not present"
        facade.show("some name we don't have")
        then:
        thrown(ProblemNotFoundException)
    }

    def "shoud list problems"() {
        given: "we have two problems in system"
        facade.addProblem(easyProblem)
        facade.addProblem(mediumProblem)

        when: "we ask for all problems"
        Page<ProblemDto> foundFilms = facade.findAll(new PageRequest(0, 10))

        then: "system returns the problems we have added"
        foundFilms.contains(easyProblem)
        foundFilms.contains(mediumProblem)
    }
}
