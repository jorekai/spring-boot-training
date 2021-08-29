package com.example.demo.habit;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Habit {
    @Id
    @SequenceGenerator(
            name = "habit_sequence",
            sequenceName = "habit_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "habit_sequence")
    private Long id;
    private String name;
    private String description;
    private LocalDate initialDate;

    public Habit() {
    }

    public Habit(Long id, String name, String description, LocalDate initialDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialDate = initialDate;
    }

    public Habit(String name, String description, LocalDate initialDate) {
        this.name = name;
        this.description = description;
        this.initialDate = initialDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", initialDate=" + initialDate +
                '}';
    }
}

