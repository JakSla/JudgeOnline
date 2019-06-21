package pl.ejaksla.onlinejudge.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements java.io.Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String userName;
    private String firstName;
    private String lastName;

    private Long accountType;

    @OneToMany(mappedBy = "user")
    private Set<Problem> problems = new HashSet<>();

    public User() {
    }

    public User(String userName, String firstName, String lastName, Long accountType, Set<Problem> problems) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountType = accountType;
        this.problems = problems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAccountType() {
        return accountType;
    }

    public void setAccountType(Long accountType) {
        this.accountType = accountType;
    }

    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }
}
