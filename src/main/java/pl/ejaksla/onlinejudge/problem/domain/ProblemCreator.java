package pl.ejaksla.onlinejudge.problem.domain;

import pl.ejaksla.onlinejudge.problem.dto.ProblemDto;

/**
 * Created by Jakub Slawinski on 2019-11-13
 */
class ProblemCreator {

    Problem from(final ProblemDto problemDto) {
        return Problem.builder()
                .name(problemDto.getName())
                .description(problemDto.getDescription())
                .sampleInput(problemDto.getSampleInput())
                .sampleOutput(problemDto.getSampleOutput())
                .hiddenInput(problemDto.getHiddenInput())
                .hiddenOutput(problemDto.getHiddenOutput())
                .build();
    }
}
