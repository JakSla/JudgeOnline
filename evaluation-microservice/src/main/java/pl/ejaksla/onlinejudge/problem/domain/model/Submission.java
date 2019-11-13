package pl.ejaksla.onlinejudge.problem.domain.model;

public class Submission {

    private Long id;

    private String submittedSolution;
    private String language;
    //TODO should a problem be mapped here?


    public Submission(String submittedSolution, String language) {
        this.submittedSolution = submittedSolution;
        this.language = language;
    }

    public String getSubmittedSolution() {
        return submittedSolution;
    }

    public void setSubmittedSolution(String submittedSolution) {
        this.submittedSolution = submittedSolution;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
