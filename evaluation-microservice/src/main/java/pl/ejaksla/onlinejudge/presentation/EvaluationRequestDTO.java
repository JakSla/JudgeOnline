package pl.ejaksla.onlinejudge.presentation;

public class EvaluationRequestDTO {

    private Long userID;
    private Long problemID;

    private String submittedCode;
    private String hiddenTestCasesCode;
    private String publicTestCasesCode;

    private String programmingLanguage;
    private Boolean isFinalSubmission;

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

    public String getSubmittedCode() {
        return submittedCode;
    }

    public void setSubmittedCode(String submittedCode) {
        this.submittedCode = submittedCode;
    }

    public String getHiddenTestCasesCode() {
        return hiddenTestCasesCode;
    }

    public void setHiddenTestCasesCode(String hiddenTestCasesCode) {
        this.hiddenTestCasesCode = hiddenTestCasesCode;
    }

    public String getPublicTestCasesCode() {
        return publicTestCasesCode;
    }

    public void setPublicTestCasesCode(String publicTestCasesCode) {
        this.publicTestCasesCode = publicTestCasesCode;
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

    @Override
    public String toString() {
        return "EvaluationRequestDTO{" +
                "userID=" + userID +
                ", problemID=" + problemID +
                ", submittedCode='" + submittedCode + '\'' +
                ", hiddenTestCasesCode='" + hiddenTestCasesCode + '\'' +
                ", publicTestCasesCode='" + publicTestCasesCode + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                ", isFinalSubmission=" + isFinalSubmission +
                '}';
    }
}
