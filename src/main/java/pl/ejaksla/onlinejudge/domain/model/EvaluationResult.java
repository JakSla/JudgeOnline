package pl.ejaksla.onlinejudge.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EvaluationResult {

    @Id
    private Long id;

    //TODO enum for each possible state
    /**
     * Result state -> compilationFailed, someConstraintsFailed, submissionSuccessful? etc
     */
    private String result;

    //TODO Create testResults class
    /**
     * Number of completed/failed test cases with state for each one
     */
    private String testsResult;

    public EvaluationResult(String result, String testsResult) {
        this.result = result;
        this.testsResult = testsResult;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTestsResult() {
        return testsResult;
    }

    public void setTestsResult(String testsResult) {
        this.testsResult = testsResult;
    }
}
