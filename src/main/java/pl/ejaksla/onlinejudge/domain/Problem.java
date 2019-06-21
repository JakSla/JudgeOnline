package pl.ejaksla.onlinejudge.domain;

import javax.persistence.*;

@Entity
@Table(name = "problems")
public class Problem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="problem_id")
    private User user;

    private String problemName;
    //should be html, perhaps with pictures
    private String problemDescription;

    private String sampleInput;
    private String sampleOutput;

    private String fullInput;
    private String fullOutput;

    public Problem() {
    }

    public Problem(User user, String problemName, String problemDescription, String sampleInput, String sampleOutput, String fullInput, String fullOutput) {
        this.user = user;
        this.problemName = problemName;
        this.problemDescription = problemDescription;
        this.sampleInput = sampleInput;
        this.sampleOutput = sampleOutput;
        this.fullInput = fullInput;
        this.fullOutput = fullOutput;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }

    public String getSampleOutput() {
        return sampleOutput;
    }

    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    public String getFullInput() {
        return fullInput;
    }

    public void setFullInput(String fullInput) {
        this.fullInput = fullInput;
    }

    public String getFullOutput() {
        return fullOutput;
    }

    public void setFullOutput(String fullOutput) {
        this.fullOutput = fullOutput;
    }
}
