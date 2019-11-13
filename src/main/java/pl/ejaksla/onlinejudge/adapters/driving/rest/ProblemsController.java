package pl.ejaksla.onlinejudge.adapters.driving.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ejaksla.onlinejudge.problem.domain.ProblemFacade;
import pl.ejaksla.onlinejudge.problem.dto.ProblemDto;

/**
 * Created by Jakub Slawinski on 2019-11-13
 */
@RestController()
@RequestMapping("/v1/api/problems")
public class ProblemsController {
    private ProblemFacade problemFacade;

    public ProblemsController(ProblemFacade problemFacade) {
        this.problemFacade = problemFacade;
    }

    @GetMapping()
    Page<ProblemDto> getProblems(Pageable pageable) {
        return problemFacade.findAll(pageable);
    }
}
