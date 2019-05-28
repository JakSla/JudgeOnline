package pl.ejaksla.onlinejudge.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.ejaksla.onlinejudge.domain.services.impl.JavaScoringServiceImpl;

@RestController
@RequestMapping("/v1/api")
public class EvaluationController {

    private final JavaScoringServiceImpl javaScoringServiceImpl;

    @Autowired
    public EvaluationController(JavaScoringServiceImpl javaScoringServiceImpl) {
        this.javaScoringServiceImpl = javaScoringServiceImpl;
    }

    @PostMapping("/evaluate")
    public EvaluationResponseDTO evaluate(@RequestParam(name = "input") final String input,
                                          @RequestParam(name = "output") final String output,
                                          @RequestParam(name = "programmingLanguage") final String programmingLanguage,
                                          @RequestBody final String submittedCode) {
        //TODO Put everything into JSON
        //TODO Base64 submitted code
        System.out.println("Programming language is :");
        System.out.println(programmingLanguage);
        System.out.println("Submitted code:");
        System.out.println(submittedCode);
        System.out.println("Input:");
        System.out.println(input);
        System.out.println("Output:");
        System.out.println(output);

        //TODO create wrapper class to have status, results, execution time, error message etc
        final String result;
        switch (programmingLanguage) {
            case "JAVA":
                result = javaScoringServiceImpl.evaluateSolution(submittedCode,input, output);
                break;
            case "CPP":
                result = "NOT IMPLEMENTED";
                break;
            case "PYTHON3":
                result = "NOT IMPLEMENTED";
                break;
            default:
                result = "PROGRAMMING LANGUAGE NOT AVAILABLE";
        }

        return new EvaluationResponseDTO(EvaluationResponseDTO.Status.OK, result);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    String handleException(final IllegalArgumentException e) {
        return e.getMessage();
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "OK";
    }
}
