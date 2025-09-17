package com.amblessed.todosrestapi.entity;



/*
 * @Project Name: todos-rest-api
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17-Sep-25
 */

import jakarta.persistence.*;
import lombok.*;

@Table(name = "todos")
@Entity
@Getter
@Setter
@NoArgsConstructor    // Default constructor (required by JPA)
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "priority", nullable = false)
    private int priority;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private User owner;

    /*Table post will have a user_id column pointing to user.id.
        Each post must have an owner (nullable = false).
        The User object is only fetched when you call post.getOwner() (lazy loading).
    */
}
