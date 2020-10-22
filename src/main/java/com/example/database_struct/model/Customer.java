package com.example.database_struct.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/** EXAMPLE 1: OneToMany relationship
 *  A customer can have many orders,
 *  But an order can only belong to one user
 */

@Entity
@Table(name = "customers")
@Getter
@NoArgsConstructor
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Setter
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
