package pl.ejaksla.onlinejudge.adapters.driving.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.ResultActions
import pl.ejaksla.onlinejudge.base.IntegrationSpec
import pl.ejaksla.onlinejudge.problem.domain.ProblemFacade
import pl.ejaksla.onlinejudge.problem.domain.SampleProblems
import spock.lang.Ignore

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Ignore
class ProblemControllerTest extends IntegrationSpec implements SampleProblems {

    @Autowired
    ProblemFacade problemFacade

    @WithMockUser
    def "should get problems"() {
        given: 'inventory has "easyProblem" and "mediumProblem'
        problemFacade.addProblem(easyProblem)
        problemFacade.addProblem(mediumProblem)

        when: 'I go to /v1/api/problems'
        ResultActions getProblems = mockMvc.perform(get("/v1/api/problems"))

        then: 'I see all problems'
        getProblems.andExpect(status().isOk())
                .andExpect(content().json("""
                {
                    "content": [
                        {"name":"$easyProblem.name","description":"$easyProblem.description", "sampleInput":"$easyProblem.sampleInput", "sampleOutput":"$easyProblem.sampleOutput", "hiddenInput":"$easyProblem.hiddenInput", "hiddenOutput":"$easyProblem.hiddenOutput"},
                        {"name":"$mediumProblem.name","description":"$mediumProblem.description", "sampleInput":"$mediumProblem.sampleInput", "sampleOutput":"$mediumProblem.sampleOutput", "hiddenInput":"$mediumProblem.hiddenInput", "hiddenOutput":"$mediumProblem.hiddenOutput"}
                    ]
                }"""))

        when: 'I go to /v1/api/problems'
        ResultActions getFilm = mockMvc.perform(get("/v1/api/problems/$easyProblem.name"))

        then: 'I see details of that problem'
        getFilm.andExpect(status().isOk())
                .andExpect(content().json("""
                        {"name":"$easyProblem.name","description":"$easyProblem.description", "sampleInput":"$easyProblem.sampleInput", "sampleOutput":"$easyProblem.sampleOutput", "hiddenInput":"$easyProblem.hiddenInput", "hiddenOutput":"$easyProblem.hiddenOutput"},
                """))
    }
}
