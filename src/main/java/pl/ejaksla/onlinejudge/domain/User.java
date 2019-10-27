package pl.ejaksla.onlinejudge.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
class User implements java.io.Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String username;
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    private Set<Role> roles;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "user")
    private Set<Problem> problems;

    @Builder
    public User(final String username, final String firstName, final String lastName, Set<Problem> problems) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.problems = problems;
    }

}
