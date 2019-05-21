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
    public EvaluationResponseDTO evaluate(@RequestParam(name = "problemId") final String problemID,
                                          @RequestParam(name = "programmingLanguage") final String programmingLanguage,
                                          @RequestParam(name = "isFinalSubmission") final boolean isFinalSubmission,
                                          @RequestBody final EvaluationRequestDTO evaluationRequest) {

        // 1. Retrieved arguments
        System.out.println("ProblemID = " + problemID);
        System.out.println("Programming language is " + programmingLanguage);
        System.out.println("Is it final submission? " + isFinalSubmission);
        System.out.println("Submitted code:");
        System.out.println(evaluationRequest.getSubmittedCode());
        System.out.println("Hidden tests code:");
        System.out.println(evaluationRequest.getHiddenTestCasesCode());
        System.out.println("Public tests code:");
        System.out.println(evaluationRequest.getPublicTestCasesCode());

        final String result;
        switch (programmingLanguage) {
//            case "JAVA":
//                result = javaScoringServiceImpl.evaluateSolution(evaluationRequest.getSubmittedCode(), problemID, isFinalSubmission);
//                break;
            case "CPP":
                result = "NOT IMPLEMENTED";
                break;
            case "PYTHON3":
                result = "NOT IMPLEMENTED";
                break;
            default:
                result = "empty";
        }

        return new EvaluationResponseDTO(EvaluationResponseDTO.Status.OK, result);

        // 2. For a given question either:
        /**
         * A) execute everything in given file (have a 1 file) where just Solution() method body is visible to edit
         * and test cases are hardcoded within this 1 file. Execution would be via main() method in this file.
         *
         * B) have separated file with test cases and execute them as well via gradle/maven? or something.
         *
         * in both cases input from web/file needs to be incorporated to the file sent for compilation/evaluation.
         */

        /** Example file:
         * import java.util.HashMap;
         *
         * class Program.java { //unchangable
         *   public static int[] twoNumberSum2(int[] array, int targetSum) { //unchangable
         *     HashMap<Integer, Boolean> nums = new HashMap<>();
         * 		for (int num : array) {
         * 			int potentialMatch = targetSum - num;
         *
         * 			if (nums.containsKey(potentialMatch)) {
         * 				return potentialMatch > num ? new int[] {num, potentialMatch} : new int[] {potentialMatch, num};
         *                        } else {
         * 				nums.put(num, true);
         *            }* 		}
         * 		return new int[0];
         *   }
         * }
         */

        // 5. Return results and parse. (result/fails ratio, raw output, list of failed TCs)
        // 6. Print to user. (via cmdline or to web)
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
