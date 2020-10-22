package com.example.database_struct.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


/** EXAMPLE 1: OneToMany relationship
 *  A customer can have many orders,
 *  But an order can only belong to one user
 */

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Setter
    private String number;

    @ManyToOne
    private Customer customer;

    public Order(Customer customer) {
        this.customer = customer;
    }
}
