package org.han.webtest.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class CourseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false , length = 100)
    private String courseName;

    @Column(nullable = false, unique = true , length = 100)
    private int courseCapacity;

}
