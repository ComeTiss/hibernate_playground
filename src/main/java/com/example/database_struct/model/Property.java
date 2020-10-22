package com.example.database_struct.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/** EXAMPLE 2: ManyToMany relationship

 A User can like many properties,
 &  properties can be liked by many users
 */

@Entity
@Table(name = "properties")
@Getter
@NoArgsConstructor
public class Property implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Setter
    private String address;

    @Setter
    @ManyToMany(
            mappedBy = "properties",
            targetEntity = User.class,
            cascade = CascadeType.ALL
    )
    private Set<User> users = new HashSet<User>();

    public Property(String address) {
        this.address = address;
    }
}
