package com.example.demo.habit;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
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

    @ElementCollection
    @ApiModelProperty(dataType = "[Ljava.lang.String;")
    private List<LocalDate> habitDates = new ArrayList<>();

    public Habit() {
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

    public List<LocalDate> getHabitDates() {
        return habitDates;
    }

    public void setHabitDates(List<LocalDate> habitDates) {
        this.habitDates = habitDates;
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

