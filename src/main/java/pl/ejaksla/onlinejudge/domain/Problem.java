package pl.ejaksla.onlinejudge.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "problems")
@Data
public class Problem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="problem_id")
    private User user;

    private String problemName;
    private String problemDescription;

    private String sampleInput;
    private String sampleOutput;

    private String fullInput;
    private String fullOutput;

    @Builder
    public Problem(User user, String problemName, String problemDescription, String sampleInput, String sampleOutput, String fullInput, String fullOutput) {
        this.user = user;
        this.problemName = problemName;
        this.problemDescription = problemDescription;
        this.sampleInput = sampleInput;
        this.sampleOutput = sampleOutput;
        this.fullInput = fullInput;
        this.fullOutput = fullOutput;
    }

}
