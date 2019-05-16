package pl.ejaksla.onlinejudge.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EvaluationResult {

    @Id
    private Long id;
    private Long userID;
    private Long problemID;

    private String compilationResult;
    private String testsResult;

    private String submittedCode;
    private String programmingLanguage;
    private Boolean isFinalSubmission;


    public EvaluationResult(Long userID, Long problemID, String compilationResult, String testsResult,
                            String submittedCode, String programmingLanguage, Boolean isFinalSubmission) {
        this.userID = userID;
        this.problemID = problemID;
        this.compilationResult = compilationResult;
        this.testsResult = testsResult;
        this.submittedCode = submittedCode;
        this.programmingLanguage = programmingLanguage;
        this.isFinalSubmission = isFinalSubmission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getProblemID() {
        return problemID;
    }

    public void setProblemID(Long problemID) {
        this.problemID = problemID;
    }

    public String getCompilationResult() {
        return compilationResult;
    }

    public void setCompilationResult(String compilationResult) {
        this.compilationResult = compilationResult;
    }

    public String getTestsResult() {
        return testsResult;
    }

    public void setTestsResult(String testsResult) {
        this.testsResult = testsResult;
    }

    public String getSubmittedCode() {
        return submittedCode;
    }

    public void setSubmittedCode(String submittedCode) {
        this.submittedCode = submittedCode;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public Boolean getFinalSubmission() {
        return isFinalSubmission;
    }

    public void setFinalSubmission(Boolean finalSubmission) {
        isFinalSubmission = finalSubmission;
    }
}
