package org.han.webtest.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="membership_plans")
public class MembershipPlanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    public MembershipPlanModel() {}

    public MembershipPlanModel(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}