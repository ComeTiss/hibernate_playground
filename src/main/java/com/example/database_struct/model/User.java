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
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Setter
    private String name;

    // Note: FetchType.LAZY is preferred for performance consideration
    @Setter
    @ManyToMany(targetEntity = Property.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name="liked_properties",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="property_id")
    )
    private Set<Property> properties = new HashSet<Property>();

    public User(String name) {
        this.name = name;
    }

    public void likeProperty(Property property) {
        if (this.properties == null) {
            this.properties = new HashSet();
        }
        this.properties.add(property);
    }

    public void unlikeProperty(Property property) {
        if (this.properties == null) {
            return;
        }
        this.properties.remove(property);
    }
}
