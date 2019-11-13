package pl.ejaksla.onlinejudge.problem.domain.model;

public class Problem {

    private Long id;
    private String description;
    private String testCases;

    public Problem(String description, String testCases) {
        this.description = description;
        this.testCases = testCases;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTestCases() {
        return testCases;
    }

    public void setTestCases(String testCases) {
        this.testCases = testCases;
    }
}
