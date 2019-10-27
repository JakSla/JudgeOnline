package pl.ejaksla.onlinejudge.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Jakub Slawinski on 2019-10-27
 */

@Entity
@Data
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
