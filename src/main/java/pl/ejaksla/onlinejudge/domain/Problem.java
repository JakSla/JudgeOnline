package pl.ejaksla.onlinejudge.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "PROBLEMS")
@Data
@DynamicUpdate
public class Problem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Version
    private int version;

    @EqualsAndHashCode.Include
    private UUID uuid = UUID.randomUUID();

    private String name;
    private String description;

    private String sampleInput;
    private String sampleOutput;

    private String hiddenInput;
    private String hiddenOutput;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Builder
    public Problem(final String name,
                   final String problemDescription,
                   final String sampleInput,
                   final String sampleOutput,
                   final String hiddenInput,
                   final String hiddenOutput) {
        this.name = name;
        this.description = problemDescription;
        this.sampleInput = sampleInput;
        this.sampleOutput = sampleOutput;
        this.hiddenInput = hiddenInput;
        this.hiddenOutput = hiddenOutput;
    }
}
