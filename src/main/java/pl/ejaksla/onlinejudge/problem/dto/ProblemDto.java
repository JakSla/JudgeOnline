package pl.ejaksla.onlinejudge.problem.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by Jakub Slawinski on 2019-11-13
 */
@Builder
@Getter
@EqualsAndHashCode
public class ProblemDto {

    private String name;
    private String description;

    private String sampleInput;
    private String sampleOutput;

    private String hiddenInput;
    private String hiddenOutput;

}
