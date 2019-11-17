package pl.ejaksla.onlinejudge.problem.domain

import groovy.transform.CompileStatic
import pl.ejaksla.onlinejudge.problem.dto.ProblemDto

@CompileStatic
trait SampleProblems {

    ProblemDto easyProblem = createProblemDto("dupa", "cos to musi byc", "1 2 3", "2", "51515 1", "5")
    ProblemDto mediumProblem = createProblemDto("dupa2", "cos to musi byyyyc", "1 2 4", "3", "5151225 1", "51")

    ProblemDto createProblemDto(String name, String description, String sampleInput, String sampleOutput, String hiddenInput, String hiddenOutput) {
        return ProblemDto.builder()
        .name(name)
        .description(description)
        .sampleInput(sampleInput)
        .sampleOutput(sampleOutput)
        .hiddenInput(hiddenInput)
        .hiddenOutput(hiddenOutput)
        .build()
    };

}
