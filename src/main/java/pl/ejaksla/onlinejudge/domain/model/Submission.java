package pl.ejaksla.onlinejudge.domain.model;

public class Submission {

    private Long id;

    private String submittedSolution;

    //TODO should a problem be mapped here?


    public Submission(String submittedSolution) {
        this.submittedSolution = submittedSolution;
    }

    public String getSubmittedSolution() {
        return submittedSolution;
    }

    public void setSubmittedSolution(String submittedSolution) {
        this.submittedSolution = submittedSolution;
    }
}
