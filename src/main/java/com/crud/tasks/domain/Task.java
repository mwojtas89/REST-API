package com.crud.tasks.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Data
@Entity(name= "tasks")
public class Task {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String title;

    @Column(name = "description")
    private String content;
}
